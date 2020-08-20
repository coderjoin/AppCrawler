package cn.fudan.security.appcrawler.provider.services;

import cn.fudan.security.appcrawler.provider.entity.SearchKeywords;

import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/21 20:29
 */
public interface SearchKeywordsServices {


    int updateSearched(int taskId, String keyword);

    List<SearchKeywords> getAllKeywords(int taskId);
}
