package cn.fudan.security.appcrawler.provider.crawler.scheduler;

import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.Task;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author qiaoying
 * @date 2018/11/15 19:55
 */
public class QueneScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler {

    private BlockingDeque<Request> queue = new LinkedBlockingDeque<>();

    @Override
    public int getLeftRequestsCount(Task task) {
        return queue.size();
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return getDuplicateRemover().getTotalRequestsCount(task);
    }

    @Override
    public Request poll(Task task) {
        return queue.poll();
    }

    @Override
    public void pushWhenNoDuplicate(Request request, Task task) {
        queue.add(request);
    }
}
