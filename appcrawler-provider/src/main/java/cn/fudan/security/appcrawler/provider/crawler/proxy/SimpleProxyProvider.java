package cn.fudan.security.appcrawler.provider.crawler.proxy;

import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiaoying
 * @date 2018/11/15 18:32
 */
public class SimpleProxyProvider implements ProxyProvider {
    private final List<Proxy> proxies;

    private final AtomicInteger pointer;

    public SimpleProxyProvider(List<Proxy> proxies) {
        this(proxies, new AtomicInteger(-1));
    }

    private SimpleProxyProvider(List<Proxy> proxies, AtomicInteger pointer) {
        this.proxies = proxies;
        this.pointer = pointer;
    }

    public static SimpleProxyProvider from(Proxy... proxies) {
        List<Proxy> proxiesTemp = new ArrayList<Proxy>(proxies.length);
        for (Proxy proxy : proxies) {
            proxiesTemp.add(proxy);
        }
        return new SimpleProxyProvider(Collections.unmodifiableList(proxiesTemp));
    }

    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {
        //Donothing
    }

    @Override
    public Proxy getProxy(Task task) {
        return proxies.get(incrForLoop());
    }

    private int incrForLoop() {
        int p = pointer.incrementAndGet();
        int size = proxies.size();
        if (p < size) {
            return p;
        }
        while (!pointer.compareAndSet(p, p % size)) {
            p = pointer.get();
        }
        return p % size;
    }
}
