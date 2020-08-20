package cn.fudan.security.appcrawler.provider.crawler.processor.all;


import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;
import cn.fudan.security.appcrawler.provider.entity.Appinfo;
import cn.fudan.security.appcrawler.provider.services.AppInfoServices;
import cn.fudan.security.appcrawler.provider.services.SearchKeywordsServices;
import cn.fudan.security.appcrawler.provider.services.TaskInfoServices;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author qiaoying
 * @date 2018/11/20 22:19
 */
@Component
public class BaiduAllPageProcessor implements PageProcessor {

    private Logger logger = LoggerFactory.getLogger(BaiduAllPageProcessor.class);

    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    private Site site = Site.init().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Resource
    private SearchKeywordsServices searchKeywordsServices;

    @Resource
    private TaskInfoServices taskInfoServices;

    @Resource
    private AppInfoServices appInfoServices;


    @Override
    public void process(Page page) {
        String url = page.getUrl().toString();
        String keyword = url.replace("http://shouji.baidu.com/s?wd="," ").replace("&data_type=app&f=header_app%40input%40btn_search"," ").trim();
        searchKeywordsServices.updateSearched(getTaskId(),keyword);
        String html = "";
        Document doc = page.getHtml().getDocument();
        doc.getElementsByClass("no-result").first();
        Element htmlContent = doc.getElementsByClass("result-summary").first();
        Element tmp = htmlContent.getElementsByClass("num").first();
        int pageTotal = Integer.parseInt(tmp.text());
        //System.out.println("总共"+pageTotal/10+"页");
        logger.info("{} has {} pages", keyword,pageTotal/10);
        int pageNum = 0;
        while(pageNum < (pageTotal/10)) {
            RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
            CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
                    .setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13").build();

            HttpGet httpget = new HttpGet("http://shouji.baidu.com/s?data_type=app&multi=0&ajax=1&wd=" + keyword + "&page=" + pageNum);

            //System.out.println("输出" + "http://shouji.baidu.com/s?data_type=app&multi=0&ajax=1&wd=" + keyword + "&page=" + pageNum);
            CloseableHttpResponse response = null; //拿到response
            try {
                response = httpclient.execute(httpget);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                try {
                    html = EntityUtils.toString(entity, Charset.defaultCharset());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    EntityUtils.consume(entity);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Document doc1 = Jsoup.parse(html);

                htmlContent = doc1.getElementsByClass("app-list").first();
                Elements elements = htmlContent.getElementsByTag("li");
                for (Element element : elements) {
                    Element app4 = element.getElementsByClass("download-num").first();
                    String download = app4.text();
                    Element app3 = element.getElementsByTag("a").first();
                    String id = app3.attr("href");
                    Element app = element.getElementsByClass("little-install").first();
                    Element app2 = app.getElementsByTag("a").first();
                    String appName = app2.attr("data_name");
                    String pkgName = app2.attr("data_package");
                    String versionName = app2.attr("data_versionname");
                    String appDownUrl = app2.attr("data_url");
                    String appSize = app2.attr("data_size");

                    Appinfo appInfo = new Appinfo();
                    appInfo.setId(id);
                    appInfo.setAppname(appName);
                    appInfo.setPkgname(pkgName);
                    appInfo.setVersionname(versionName);
                    appInfo.setAppsize(appSize);
                    appInfo.setAppdowncount(download);
                    appInfo.setAppdownurl(appDownUrl);

                    System.out.println("AppInfo:" + appInfo.toString());
                    String tableName = taskInfoServices.getTaskInfoByTaskId(getTaskId()).getTablename();
                    appInfoServices.insertApp(tableName,appInfo);

                }
                ++pageNum;
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
