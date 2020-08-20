package cn.fudan.security.appcrawler.consumer.services.impl;

import cn.fudan.security.appcrawler.consumer.dao.SearchKeywordsMapper;
import cn.fudan.security.appcrawler.consumer.services.SearchKeywordsServices;
import cn.fudan.security.appcrawler.consumer.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/21 20:30
 */
@Service("searchKeywordsServices")
public class SearchKeywordsServicesImpl implements SearchKeywordsServices {

    @Autowired
    private SearchKeywordsMapper searchKeywordsMapper;

    @Override
    public int createTable(int taskId) {
        String tableName = "search_keywords_"+ taskId;
        searchKeywordsMapper.createTable(tableName);
        Integer result = searchKeywordsMapper.copyTableFromOrigin(tableName);
        if (result == null){
            return 0;
        }else {
            return result;
        }

    }

    @Override
    public int updateSearched(int taskId, String keyword) {
        String tableName = "search_keywords_"+ taskId;
        return searchKeywordsMapper.updateSearched(tableName, keyword);
    }

    @Override
    public List<String> getAllKeywords(int taskId) {
        String tableName = "search_keywords_"+ taskId;
        return searchKeywordsMapper.getAllKeywords(tableName);
    }
}
