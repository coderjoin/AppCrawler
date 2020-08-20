package cn.fudan.security.appcrawler.provider.crawler.processor.top;

import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;
import cn.fudan.security.appcrawler.provider.crawler.selector.Html;
import cn.fudan.security.appcrawler.provider.services.AppInfoServices;
import cn.fudan.security.appcrawler.provider.services.TaskInfoServices;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiaoying
 * @date 2018/12/12 15:02
 */
@Component
public class GooglePlayTopPageProcessor implements PageProcessor {


    @Autowired
    private TaskInfoServices taskInfoServices;

    @Autowired
    private AppInfoServices appInfoServices;

    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    private Site site = Site.init()
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.75 Safari/537.36")
            .setRetryTimes(3).setSleepTime(1000);
    @Override
    public void process(Page page) {
        Document doc = page.getHtml().getDocument();
        Element htmlContent = doc.getElementsByClass("main-content").first();
        Elements elements = htmlContent.getElementsByClass("title");
        for (Element app : elements){
            String href = app.attr("href");
            String pkgName = href.substring(href.indexOf("?id=") + 4);

            String appName = app.text();
            System.out.println(pkgName);
            System.out.println("appName: " + appName );
        }


    }

    @Override
    public Site getSite() {
        return site;
    }
}
