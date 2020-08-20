package cn.fudan.security.appcrawler.provider.impl;

import cn.fudan.security.appcrawler.AppcrawlerService;
import cn.fudan.security.appcrawler.provider.crawler.Crawler;
import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.processor.all.BaiduAllPageProcessor;
import cn.fudan.security.appcrawler.provider.urlmaker.RequestsFactory;

import javax.annotation.Resource;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * @author qiaoying
 * @date 2018/9/21 10:58
 */

public class AppCrawlerServiceImpl implements AppcrawlerService {

    @Resource
    private RequestsFactory requestsFactory;


    @Override
    public String sayHello(String name) {
        return name + " hello world";
    }

    @Override
    public int startCrawler(int taskId) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        System.out.println(name);
        System.out.println("Process ID: " + name.substring(0, name.indexOf("@")));
        List<Request> requests = requestsFactory.getRequests(taskId);
        System.out.println("urls:" + requests.size());
        Crawler.createWithTask(new BaiduAllPageProcessor()).startRequest(requests).thread(2);
        return 0;
    }
}
