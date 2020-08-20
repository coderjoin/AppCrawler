package cn.fudan.security.appcrawler.provider.crawler.processor.top;

import cn.fudan.security.appcrawler.consumer.entity.Appinfo;
import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author qiaoying
 * @date 2018/11/20 10:19
 */
public class QihooTopPageProcessor implements PageProcessor {

    private Site site = Site.init().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        Document doc = page.getHtml().getDocument();
        Element content = doc.getElementById("iconList");
        Elements elements = content.getElementsByTag("li");
        for (Element element : elements) {
            Element name = element.getElementsByTag("h3").first();
            String appName = name.text();

            Element app = element.getElementsByTag("span").first();
            String appDownCount = app.text();
            Element link = element.getElementsByTag("a").last();
            //System.out.println(link);
            String id = link.attr("sid");
            String href = link.attr("href");
            String appDownUrl = href.substring(href.indexOf("url=") + 4);
            System.out.println(appName);
            //String categoryName = CATEGORIES.get(cid);


            System.out.println("top app crawlered with id: " + id);
            Appinfo appInfo = new Appinfo();
            appInfo.setId(id);
            appInfo.setAppname(appName);
            appInfo.setAppdowncount(appDownCount);
            appInfo.setAppdownurl(appDownUrl);
            //appInfo.setCategoryname(categoryName);
        }
    }

    @Override
    public Site getSite() {
        return  site;
    }
}
