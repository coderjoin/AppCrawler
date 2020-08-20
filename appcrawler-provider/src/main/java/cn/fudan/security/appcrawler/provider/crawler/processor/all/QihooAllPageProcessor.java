package cn.fudan.security.appcrawler.provider.crawler.processor.all;

import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;
import cn.fudan.security.appcrawler.provider.entity.Appinfo;
import cn.fudan.security.appcrawler.provider.services.AppInfoServices;
import cn.fudan.security.appcrawler.provider.services.SearchKeywordsServices;
import cn.fudan.security.appcrawler.provider.services.TaskInfoServices;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qiaoying
 * @date 2018/11/20 22:20
 */
@Component
public class QihooAllPageProcessor implements PageProcessor {
    private Logger logger = LoggerFactory.getLogger(BaiduAllPageProcessor.class);

    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    private Site site = Site.init()
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
            .setRetryTimes(3)
            .addCookie("Cookie", "PHPSESSID=v1t31anh1ku5291ve4pon0e4n59vd7c72uun04922cubicp5m8o1; cs6k_langid=zh_cn; _gat_UA-7728030-4=1; cs6k_2f6c74b2aa33f495a5477460e731939caf1da42f718802b1ac7a1bc32da79a7a=s%3A45%3A%22http%3A%2F%2Fapp.hicloud.com%2Fplugin%2Fappstore%2Fsearch%22%3B; __gahuawei=GA1.2.47233297.1508917370; __gahuawei_gid=GA1.2.614504483.1508917370")
            .setSleepTime(1000)
            .setUseGzip(true)
            .setTimeOut(10000);

    @Resource
    private SearchKeywordsServices searchKeywordsServices;

    @Resource
    private TaskInfoServices taskInfoServices;

    @Resource
    private AppInfoServices appInfoServices;

    @Override
    public void process(Page page) {
        String html = "";
        String url = page.getUrl().toString();
        String keyword = url.replace("http://zhushou.360.cn/search/index/?kw="," ").trim();
        searchKeywordsServices.updateSearched(getTaskId(),keyword);
        Document doc = page.getHtml().getDocument();
        int pageTotal = 0;
        try {
            Element htmlContent = doc.getElementsByClass("title_tr").first();
            Element tmp = htmlContent.getElementsByTag("span").first();
            pageTotal = Integer.parseInt(tmp.text());
            int pageNum = 0;
            while(pageNum < (pageTotal/15)){
                Connection con = Jsoup.connect("http://zhushou.360.cn/search/index/?kw="+keyword+"&page="+pageNum);
                doc = con.get();
                htmlContent = doc.getElementsByClass("SeaCon").first();
                Elements elements = htmlContent.getElementsByTag("li");
                for (Element element : elements) {
                    Element app1 = element.getElementsByTag("h3").first();
                    Element app2 = app1.getElementsByTag("a").first();
                    String appName = app2.attr("title");
                    System.out.println(appName);
                    Element app3 = element.getElementsByClass("sdlft").first();
                    Element app4 = app3.getElementsByClass("downNum").first();
                    String download = app4.text();
                    Element app5 = element.getElementsByClass("download").first();
                    Element app6 = app5.getElementsByTag("a").first();
                    String id = app6.attr("sid");
                    String appDownUrl = app6.attr("href");
                    Appinfo appInfo = new Appinfo();
                    appInfo.setAppname(appName);
                    appInfo.setAppdownurl(appDownUrl);
                    appInfo.setId(id);
                    appInfo.setAppdowncount(download);

                    System.out.println("AppInfo:" + appInfo.toString());
                    String tableName = taskInfoServices.getTaskInfoByTaskId(getTaskId()).getTablename();
                    appInfoServices.insertApp(tableName,appInfo);

                }

                ++pageNum;
            }

        } catch (Exception e) {

        }






    }

    @Override
    public Site getSite() {
        return site;
    }
}
