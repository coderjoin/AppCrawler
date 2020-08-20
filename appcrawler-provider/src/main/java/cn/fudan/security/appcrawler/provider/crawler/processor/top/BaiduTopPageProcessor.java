package cn.fudan.security.appcrawler.provider.crawler.processor.top;


import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;
import cn.fudan.security.appcrawler.provider.entity.Appinfo;
import cn.fudan.security.appcrawler.provider.services.AppInfoServices;
import cn.fudan.security.appcrawler.provider.services.TaskInfoServices;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiaoying
 * @date 2018/11/19 16:08
 */
@Component
public class BaiduTopPageProcessor implements PageProcessor {

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

    private Site site = Site.init().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        Document doc = page.getHtml().getDocument();
        Element htmlContent = doc.getElementsByClass("app-bd").first();
        Elements elements = htmlContent.getElementsByTag("li");
        for (Element element : elements) {
            Element app2 = element.getElementsByTag("a").first();
            String id = app2.attr("href");
            Element appElement1 = element.getElementsByClass("down-size").first();
            Element app1 = appElement1.getElementsByTag("span").first();
            String download = app1.text();
            Element appElement = element.getElementsByClass("down-btn").first();
            Element app = appElement.getElementsByTag("span").first();
            String appName = app.attr("data_name");
            String pkgName = app.attr("data_package");
            String versionName = app.attr("data_versionname");
            String appDownUrl = app.attr("data_url");
            String appSize = app.attr("data_size");
            //String categoryName = CATEGORIES.get(cid);


            System.out.println("top app crawlered with id: " + id);
            Appinfo appInfo = new Appinfo();
            appInfo.setId(id);
            appInfo.setAppname(appName);
            appInfo.setPkgname(pkgName);
            appInfo.setVersionname(versionName);
            appInfo.setAppsize(appSize);
            appInfo.setAppdowncount(download);
            appInfo.setAppdownurl(appDownUrl);
            //appInfo.setCategoryname(categoryName);

            System.out.println("AppInfo:" + appInfo.toString());
            String tableName = taskInfoServices.getTaskInfoByTaskId(getTaskId()).getTablename();
            appInfoServices.insertApp(tableName,appInfo);
        }
    }

    @Override
    public Site getSite() {
        return  site;
    }
}
