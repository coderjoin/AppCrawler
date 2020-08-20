package cn.fudan.security.appcrawler.provider.crawler;

/**
 * @author qiaoying
 * @date 2018/11/15 11:00
 */
public interface Task {

    String getCrawlerId();

    Site getSite();
}
