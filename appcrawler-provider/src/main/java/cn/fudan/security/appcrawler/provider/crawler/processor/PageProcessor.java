package cn.fudan.security.appcrawler.provider.crawler.processor;

import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;

/**
 * @author qiaoying
 * @date 2018/11/15 19:36
 */
public interface PageProcessor {

    void process(Page page);

    Site getSite();
}
