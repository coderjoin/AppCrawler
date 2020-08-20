package cn.fudan.security.appcrawler.provider.urlmaker;

import cn.fudan.security.appcrawler.provider.crawler.Request;

import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/16 15:13
 */
public interface RequsetMaker {

    List<Request> getRequests(int taskId);
}
