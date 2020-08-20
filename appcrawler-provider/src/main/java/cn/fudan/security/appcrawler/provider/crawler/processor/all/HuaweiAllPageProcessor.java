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
 * @date 2018/11/20 22:19
 */
@Component
public class HuaweiAllPageProcessor implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(BaiduAllPageProcessor.class);

    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Resource
    private SearchKeywordsServices searchKeywordsServices;

    @Resource
    private TaskInfoServices taskInfoServices;

    @Resource
    private AppInfoServices appInfoServices;

    private Site site = Site.init()
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
            .setRetryTimes(3)
            .addCookie("Cookie", "PHPSESSID=v1t31anh1ku5291ve4pon0e4n59vd7c72uun04922cubicp5m8o1; cs6k_langid=zh_cn; _gat_UA-7728030-4=1; cs6k_2f6c74b2aa33f495a5477460e731939caf1da42f718802b1ac7a1bc32da79a7a=s%3A45%3A%22http%3A%2F%2Fapp.hicloud.com%2Fplugin%2Fappstore%2Fsearch%22%3B; __gahuawei=GA1.2.47233297.1508917370; __gahuawei_gid=GA1.2.614504483.1508917370")
            .setSleepTime(1000)
            .setUseGzip(true)
            .setTimeOut(10000);


    @Override
    public void process(Page page) {
        try {
            String url = page.getUrl().toString();
            String keyword = url.replace("http://app.hicloud.com/search/"," ").trim();
            searchKeywordsServices.updateSearched(getTaskId(),keyword);
            String html = "";
            Document doc = page.getHtml().getDocument();
            Element element1 = doc.getElementsByClass("unit-main").first();
            Element element2 = element1.getElementsByClass("sres").first();
            String str = element2.text();
            str=str.trim();
            String str2="";
            if(str != null && !"".equals(str)){
                for(int j=0;j<str.length();j++){
                    if(str.charAt(j)>=48 && str.charAt(j)<=57){
                        str2+=str.charAt(j);
                    }
                }
            }
            int total = Integer.parseInt(str2);
            int pageNum = 1;
            while (pageNum <= total/24) {
                Connection con = Jsoup.connect("http://app.hicloud.com/search/" + keyword + "/" + pageNum);
                con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                con.header("Accept-Encoding", "gzip,deflate");
                con.header("Accept-Language", "zh-CN,zh;q=0.8");
                con.header("Accept-Encoding", "gzip,deflate");
                con.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
                con.header("Cookie", "PHPSESSID=v1t31anh1ku5291ve4pon0e4n59vd7c72uun04922cubicp5m8o1; cs6k_langid=zh_cn; _gat_UA-7728030-4=1; cs6k_2f6c74b2aa33f495a5477460e731939caf1da42f718802b1ac7a1bc32da79a7a=s%3A45%3A%22http%3A%2F%2Fapp.hicloud.com%2Fplugin%2Fappstore%2Fsearch%22%3B; __gahuawei=GA1.2.47233297.1508917370; __gahuawei_gid=GA1.2.614504483.1508917370");
                doc = con.get();
                Elements htmlContent = doc.select("div .list-game-app").select(".dotline-btn").select(".nofloat");
                for (Element element : htmlContent) {
                    Element appElement = element.getElementsByClass("app-btn").first();
                    Element appDownload = appElement.getElementsByTag("span").last();
                    String downloadCount = appDownload.text();
                    Element appElement2 = appElement.getElementsByTag("a").first();
                    String tmp = appElement2.attr("onclick");
                    tmp = tmp.substring(tmp.indexOf("(") + 1, tmp.lastIndexOf(")"));
                    String temp;
                    temp = tmp.substring(0, tmp.indexOf(","));
                    String id = temp.substring(temp.indexOf("'") + 1, temp.lastIndexOf("'"));
                    tmp = tmp.substring(temp.length() + 1);
                    temp = tmp.substring(0, tmp.indexOf(","));
                    String appName = temp.substring(temp.indexOf("'") + 1, temp.lastIndexOf("'"));
                    tmp = tmp.substring(temp.length() + 1);
                    temp = tmp.substring(0, tmp.indexOf(","));
                    String softrank = temp.substring(temp.indexOf("'") + 1, temp.lastIndexOf("'"));
                    tmp = tmp.substring(temp.length() + 1);
                    temp = tmp.substring(0, tmp.indexOf(","));
                    String number = temp.substring(temp.indexOf("'") + 1, temp.lastIndexOf("'"));
                    tmp = tmp.substring(temp.length() + 1);
                    temp = tmp.substring(0, tmp.indexOf(","));
                    String categoryName = temp.substring(temp.indexOf("'") + 1, temp.lastIndexOf("'"));
                    tmp = tmp.substring(temp.length() + 1);
                    temp = tmp.substring(0, tmp.indexOf(","));
                    String appDownloadUrl = temp.substring(temp.indexOf("'") + 1, temp.lastIndexOf("'"));
                    Appinfo appInfo = new Appinfo();
                    appInfo.setId(id);
                    appInfo.setAppname(appName);
                    appInfo.setCategoryname(categoryName);
                    appInfo.setAppdownurl(appDownloadUrl);
                    appInfo.setAppdowncount(downloadCount);

                    System.out.println("appinfo: " + appInfo.toString());
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
