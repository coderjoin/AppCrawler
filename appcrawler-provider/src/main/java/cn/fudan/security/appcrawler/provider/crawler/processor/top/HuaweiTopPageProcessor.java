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
public class HuaweiTopPageProcessor implements PageProcessor {

    private Site site = Site.init().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

       Document doc = page.getHtml().getDocument();
        Elements htmlContent = doc.select("div .list-game-app").select(".dotline-btn").select(".nofloat");
        for (Element element : htmlContent) {
            Element appElement = element.getElementsByClass("app-btn").first();
            Element appDownload = appElement.getElementsByTag("span").last();
            String downloadCount = appDownload.text();
            Element appElement2 = appElement.getElementsByTag("a").first();
            String tmp = appElement2.attr("onclick");
            tmp = tmp.substring(tmp.indexOf("(") + 1, tmp.lastIndexOf(")"));
            String temp = tmp.substring(0, tmp.indexOf(","));
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

            System.out.println("top app crawlered with id: " + id);
            Appinfo appInfo = new Appinfo();
            appInfo.setId(id);
            appInfo.setAppname(appName);
            appInfo.setCategoryname(categoryName);
            appInfo.setAppdownurl(appDownloadUrl);
            appInfo.setAppdowncount(downloadCount);
            System.out.println(appInfo.toString());

        }
    }

    @Override
    public Site getSite() {
        return  site;
    }
}
