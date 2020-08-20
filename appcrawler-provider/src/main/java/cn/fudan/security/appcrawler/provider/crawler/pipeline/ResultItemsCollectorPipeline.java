package cn.fudan.security.appcrawler.provider.crawler.pipeline;

import cn.fudan.security.appcrawler.provider.crawler.ResultItems;
import cn.fudan.security.appcrawler.provider.crawler.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/16 13:58
 */
public class ResultItemsCollectorPipeline implements CollectorPipeline<ResultItems> {

    private List<ResultItems> collector = new ArrayList<ResultItems>();

    @Override
    public synchronized void process(ResultItems resultItems, Task task) {
        collector.add(resultItems);
    }

    @Override
    public List<ResultItems> getCollected() {
        return collector;
    }
}
