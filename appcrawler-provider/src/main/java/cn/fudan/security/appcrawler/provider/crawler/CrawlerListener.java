package cn.fudan.security.appcrawler.provider.crawler;

/**
 * @author qiaoying
 * @date 2018/11/14 21:00
 */
public interface CrawlerListener {

    void onSuccess(Request request);

    void onError(Request request);
}
