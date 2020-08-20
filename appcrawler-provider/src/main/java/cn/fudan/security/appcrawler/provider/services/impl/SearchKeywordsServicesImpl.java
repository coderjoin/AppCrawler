package cn.fudan.security.appcrawler.provider.services.impl;

import cn.fudan.security.appcrawler.provider.dao.SearchKeywordsMapper;
import cn.fudan.security.appcrawler.provider.entity.SearchKeywords;
import cn.fudan.security.appcrawler.provider.services.SearchKeywordsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/21 20:30
 */
@Component
public class SearchKeywordsServicesImpl implements SearchKeywordsServices {

    @Autowired
    private SearchKeywordsMapper searchKeywordsMapper;


    @Override
    public int updateSearched(int taskId, String keyword) {
        String tableName = "search_keywords_"+ taskId;
        int result = searchKeywordsMapper.updateSearched(tableName, keyword);
        return result;
    }

    @Override
    public List<SearchKeywords> getAllKeywords(int taskId) {
        String tableName = "search_keywords_"+ taskId;
        return searchKeywordsMapper.getAllKeywords(tableName);
    }
}
