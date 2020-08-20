package cn.fudan.security.appcrawler.provider.dao;

import cn.fudan.security.appcrawler.provider.entity.SearchKeywords;
import cn.fudan.security.appcrawler.provider.entity.SearchKeywordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SearchKeywordsMapper {
    int countByExample(SearchKeywordsExample example);

    int deleteByExample(SearchKeywordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SearchKeywords record);

    int insertSelective(SearchKeywords record);

    List<SearchKeywords> selectByExample(SearchKeywordsExample example);

    SearchKeywords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SearchKeywords record, @Param("example") SearchKeywordsExample example);

    int updateByExample(@Param("record") SearchKeywords record, @Param("example") SearchKeywordsExample example);

    int updateByPrimaryKeySelective(SearchKeywords record);

    int updateByPrimaryKey(SearchKeywords record);

    int updateSearched(@Param("tableName") String tableName, @Param("keyword") String keyword);

    int copyTableFromOrigin(@Param("tableName") String tableName);

    List<SearchKeywords> getAllKeywords(@Param("tableName") String tableName);
}