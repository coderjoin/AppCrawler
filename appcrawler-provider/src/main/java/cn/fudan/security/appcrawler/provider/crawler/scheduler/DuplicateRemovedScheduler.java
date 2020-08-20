package cn.fudan.security.appcrawler.provider.crawler.scheduler;

import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.Task;
import cn.fudan.security.appcrawler.provider.crawler.scheduler.component.DuplicateRemover;
import cn.fudan.security.appcrawler.provider.crawler.scheduler.component.HashSetDuplicateRemover;
import cn.fudan.security.appcrawler.provider.crawler.utils.HttpConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiaoying
 * @date 2018/11/15 19:46
 */
public abstract class DuplicateRemovedScheduler implements Scheduler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private DuplicateRemover duplicatedRemover = new HashSetDuplicateRemover();

    public DuplicateRemover getDuplicateRemover() {
        return duplicatedRemover;
    }

    public DuplicateRemovedScheduler setDuplicateRemover(DuplicateRemover duplicatedRemover) {
        this.duplicatedRemover = duplicatedRemover;
        return this;
    }

    @Override
    public void push(Request request, Task task) {
        logger.trace("get a candidate url {}", request.getUrl());
        if (shouldReserved(request) || noNeedToRemoveDuplicate(request) || !duplicatedRemover.isDuplicate(request, task)) {
            logger.debug("push to queue {}", request.getUrl());
            pushWhenNoDuplicate(request, task);
        }
    }

    protected boolean shouldReserved(Request request) {
        return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
    }

    protected boolean noNeedToRemoveDuplicate(Request request) {
        return HttpConstant.Method.POST.equalsIgnoreCase(request.getMethod());
    }

    protected void pushWhenNoDuplicate(Request request, Task task) {

    }
}