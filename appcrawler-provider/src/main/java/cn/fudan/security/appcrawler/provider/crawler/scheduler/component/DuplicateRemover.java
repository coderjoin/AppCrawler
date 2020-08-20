package cn.fudan.security.appcrawler.provider.crawler.scheduler.component;

import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.Task;

/**
 * @author qiaoying
 * @date 2018/11/15 19:48
 */
public interface DuplicateRemover {

    boolean isDuplicate(Request request, Task task);

    void resetDuplicateCheck(Task task);

    int getTotalRequestsCount(Task task);
}
