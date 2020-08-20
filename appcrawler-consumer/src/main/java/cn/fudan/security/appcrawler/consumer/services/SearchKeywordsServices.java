package cn.fudan.security.appcrawler.consumer.services;

import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/21 20:29
 */
public interface SearchKeywordsServices {

    int createTable(int taskId);

    int updateSearched(int taskId, String keyword);

    List<String> getAllKeywords(int taskId);
}
