package cn.fudan.security.appcrawler.provider.crawler.proxy;

import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Task;

/**
 * @author qiaoying
 * @date 2018/11/15 18:26
 */
public interface ProxyProvider {

    void returnProxy(Proxy proxy, Page page, Task task);

    Proxy getProxy(Task task);
}
