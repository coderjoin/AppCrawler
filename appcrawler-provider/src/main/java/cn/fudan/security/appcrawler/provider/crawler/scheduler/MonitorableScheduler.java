package cn.fudan.security.appcrawler.provider.crawler.scheduler;

import cn.fudan.security.appcrawler.provider.crawler.Task;

/**
 * @author qiaoying
 * @date 2018/11/15 19:44
 */
public interface MonitorableScheduler extends Scheduler {

    int getLeftRequestsCount(Task task);

    int getTotalRequestsCount(Task task);
}
