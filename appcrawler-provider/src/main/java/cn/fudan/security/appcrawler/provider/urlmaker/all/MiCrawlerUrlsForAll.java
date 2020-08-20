package cn.fudan.security.appcrawler.provider.urlmaker.all;


import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.utils.UrlUtils;
import cn.fudan.security.appcrawler.provider.entity.SearchKeywords;
import cn.fudan.security.appcrawler.provider.services.SearchKeywordsServices;
import cn.fudan.security.appcrawler.provider.urlmaker.RequsetMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/22 17:00
 */
@Component
public class MiCrawlerUrlsForAll implements RequsetMaker {

    @Autowired
    private SearchKeywordsServices searchKeywordsServices;

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        List<SearchKeywords> keywords = searchKeywordsServices.getAllKeywords(taskId);
        for (int i = 0; i < keywords.size(); i++){
            for (int j = 1; i < 4; i++){
                urls.add("http://app.mi.com/searchAll?keywords="+keywords.get(i).getKeyword()+"&typeall=phone&page="+j);
            }
        }
        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
