package cn.fudan.security.appcrawler.provider.crawler.scheduler;

import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.Task;

/**
 * @author qiaoying
 * @date 2018/11/15 19:42
 */
public interface Scheduler {

    void push(Request request, Task task);

    Request poll(Task task);
}
