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
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author qiaoying
 * @date 2018/11/20 22:20
 */
@Component
public class MiAllPageProcessor implements PageProcessor {
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
            .addCookie("Cookie", "JSESSIONID=aaaE-mtGXIlzrcv3iMK6v; __utma=127562001.443440490.1508907661.1508907661.1508907661.1; __utmb=127562001.3.10.1508907661; __utmc=127562001; __utmz=127562001.1508907661.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic")
            .setSleepTime(1000)
            .setUseGzip(true)

            .setTimeOut(30000);

    @Resource
    private SearchKeywordsServices searchKeywordsServices;

    @Resource
    private TaskInfoServices taskInfoServices;

    @Resource
    private AppInfoServices appInfoServices;

    @Override
    public void process(Page page) {

        try {
            String url = page.getUrl().toString();
            String strTemp = url.replace("http://app.mi.com/searchAll?keywords=", "");
            String keyword = strTemp.substring(0,strTemp.indexOf("&typeall=phone"));
            searchKeywordsServices.updateSearched(getTaskId(),keyword);

            Document doc = page.getHtml().getDocument();
            Element htmlContent = doc.getElementsByClass("main").first();
            //System.out.println(htmlContent);
            Element app = htmlContent.getElementsByClass("container").first();
            //System.out.println(app);
            Element app2 = app.getElementsByClass("main-con").first();
            Element app3 = app2.getElementsByClass("applist").first();
            //System.out.println(app3);
            Elements elements = app3.getElementsByTag("li");
            //System.out.println(elements);
            for (Element element : elements) {
                Element app4 = element.getElementsByTag("a").first();
                String link = app4.attr("href");
                logger.info("mi has crawlered a herf : {}",link);
                try{
                    Appinfo appInfo = new Appinfo();

                    Connection con = Jsoup.connect("http://app.mi.com" + link);
//
//                    con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//                    con.header("Accept-Encoding", "gzip,deflate");
//                    con.header("Accept-Language", "zh-CN,zh;q=0.8");
//                    con.header("Accept-Encoding", "gzip,deflate");
//                    con.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
//                    con.header("Cookie", "JSESSIONID=aaaE-mtGXIlzrcv3iMK6v; __utma=127562001.443440490.1508907661.1508907661.1508907661.1; __utmb=127562001.3.10.1508907661; __utmc=127562001; __utmz=127562001.1508907661.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
                    Document doceach = con.get();
                    Element htmlContenteach = doceach.getElementsByClass("main").first();
                    Element appeach = htmlContenteach.getElementsByClass("app-intro").first();

                    Element app2each = appeach.getElementsByClass("intro-titles").first();
                    Element app3each = app2each.getElementsByTag("h3").first();
                    Element app8 = app2each.getElementsByTag("p").first();
                    Element app4each = app2each.getElementsByClass("app-info-down").first();
                    Element app5 = app4each.getElementsByTag("a").first();
                    String appName = app3each.text();
                    String authorName = app8.text();
                    String appDownloadUrl = "http://app.mi.com"+app5.attr("href");
                    Element app6 = app.getElementsByClass("details").first();
                    Elements app7 = app6.getElementsByClass("cf");
                    for (Element ele : app7){
                        Element appElement1 = ele.getElementsByTag("li").get(1);
                        String appSize = appElement1.text();
                        Element appElement2 = ele.getElementsByTag("li").get(3);
                        String versionName = appElement2.text();
                        Element appElement3 = ele.getElementsByTag("li").get(7);
                        String pkgName = appElement3.text();
                        Element appElement4 = ele.getElementsByTag("li").get(9);
                        String id = appElement4.text();

                        appInfo.setId(id);
                        appInfo.setVersionname(versionName);
                        appInfo.setPkgname(pkgName);

                        appInfo.setAppsize(appSize);
                    }

                    appInfo.setAppname(appName);
                    appInfo.setAuthorname(authorName);
                    appInfo.setAppdownurl(appDownloadUrl);

                    logger.info("appinfo:{}",appInfo.toString());
                    String tableName = taskInfoServices.getTaskInfoByTaskId(getTaskId()).getTablename();
                    appInfoServices.insertApp(tableName,appInfo);


                }catch (Exception e){

                }


            }
        } catch (Exception e) {

        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
