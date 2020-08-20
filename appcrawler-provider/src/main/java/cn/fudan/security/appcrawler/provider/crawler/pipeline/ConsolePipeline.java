package cn.fudan.security.appcrawler.provider.crawler.pipeline;

import cn.fudan.security.appcrawler.provider.crawler.ResultItems;
import cn.fudan.security.appcrawler.provider.crawler.Task;

import java.util.Map;

/**
 * @author qiaoying
 * @date 2018/11/15 19:11
 */
public class ConsolePipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
        }
    }
}
