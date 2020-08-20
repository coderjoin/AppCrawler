package cn.fudan.security.appcrawler.provider.urlmaker.top;

import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.urlmaker.RequsetMaker;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/19 10:29
 */
@Component
public class AnzhiCrawlerUrlsForTop implements RequsetMaker {

    @Override
    public List<Request> getRequests(int taskId) {
        return null;
    }
}
