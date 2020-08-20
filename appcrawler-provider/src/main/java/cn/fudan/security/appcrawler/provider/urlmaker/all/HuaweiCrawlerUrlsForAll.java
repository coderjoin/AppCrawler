package cn.fudan.security.appcrawler.provider.urlmaker.all;


import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.utils.UrlUtils;
import cn.fudan.security.appcrawler.provider.entity.SearchKeywords;
import cn.fudan.security.appcrawler.provider.services.SearchKeywordsServices;
import cn.fudan.security.appcrawler.provider.urlmaker.RequsetMaker;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/22 16:59
 */
@Component
public class HuaweiCrawlerUrlsForAll implements RequsetMaker {

    @Resource
    private SearchKeywordsServices searchKeywordsServices;

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        List<SearchKeywords> keywords = searchKeywordsServices.getAllKeywords(taskId);
        for (int i = 0; i < keywords.size(); i++){
            urls.add("http://app.hicloud.com/search/"+keywords.get(i).getKeyword());
        }
        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
