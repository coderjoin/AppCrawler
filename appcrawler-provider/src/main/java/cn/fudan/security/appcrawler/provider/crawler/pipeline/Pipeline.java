package cn.fudan.security.appcrawler.provider.crawler.pipeline;

import cn.fudan.security.appcrawler.provider.crawler.ResultItems;
import cn.fudan.security.appcrawler.provider.crawler.Task;

/**
 * @author qiaoying
 * @date 2018/11/15 19:09
 */
public interface Pipeline {

    void process(ResultItems resultItems, Task task);
}
