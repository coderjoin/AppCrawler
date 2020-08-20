package cn.fudan.security.appcrawler.provider.crawler.pipeline;

import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/16 13:56
 */
public interface CollectorPipeline<T> extends Pipeline {

    List<T> getCollected();
}
