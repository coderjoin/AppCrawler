package cn.fudan.security.appcrawler.provider.crawler.downloader;

import cn.fudan.security.appcrawler.provider.crawler.Crawler;
import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.selector.Html;

/**
 * @author qiaoying
 * @date 2018/11/15 17:59
 */
public abstract class AbstractDownloader implements Downloader{

    public Html download(String url){
        return download(url, null);
    }

    public Html download(String url, String charset){

        Page page = download(new Request(url), Site.init().setCharset(charset).toTask());
        return page.getHtml();
    }

    protected void onSuccess(Request request) {
    }

    protected void onError(Request request) {
    }

}
