package cn.fudan.security.appcrawler.provider.crawler.processor.top;

import cn.fudan.security.appcrawler.consumer.entity.Appinfo;
import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author qiaoying
 * @date 2018/11/20 10:06
 */
public class AnzhiTopPageProcessor implements PageProcessor {

    private Site site = Site.init().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        Document doc = page.getHtml().getDocument();
        Element htmlContent = doc.getElementsByClass("content").first();

        Element element = htmlContent.getElementsByClass("app_detail").first();
        Element element1 = element.getElementsByClass("detail_description").first();
        Element element3 = element1.getElementsByTag("h3").first();
        String appName = element3.text();
        Element element4 = element1.getElementsByClass("app_detail_version").first();
        String versionName = element4.text();
        versionName = versionName.substring(versionName.indexOf("(")+1, versionName.indexOf(")"));
        Element element6 = element1.getElementById("detail_line_ul");
        Element category = element6.getElementsByTag("li").first();
        String categoryName = category.text();
        System.out.println(categoryName);
        Element element5 = element1.getElementsByClass("spaceleft").get(1);
        String appSize = element5.text();
        appSize = appSize.substring(3);
        System.out.println(appSize);
        Element element2 = element.getElementsByClass("detail_down").first();
        Element link = element2.getElementsByTag("a").first();
        String url1 = link.attr("onclick");
        url1 = url1.substring(url1.indexOf("(")+1, url1.indexOf(")"));
        String url2 = "http://www.anzhi.com/dl_app.php?s="+url1+"&n=5";
        Appinfo appInfo = new Appinfo();
        appInfo.setId(url1);
        appInfo.setAppname(appName);
        //appInfo.setPkgName(pkgName);
        appInfo.setVersionname(versionName);
        appInfo.setAppsize(appSize);
        //appInfo.setAppDownCount(download);
        appInfo.setAppdownurl(url2);
        appInfo.setCategoryname(categoryName);
    }

    @Override
    public Site getSite() {
        return  site;
    }
}
