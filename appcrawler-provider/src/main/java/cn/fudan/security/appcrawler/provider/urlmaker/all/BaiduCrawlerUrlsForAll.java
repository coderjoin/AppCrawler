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
 * @date 2018/11/16 15:11
 */
@Component
public class BaiduCrawlerUrlsForAll implements RequsetMaker {

    @Resource
    private SearchKeywordsServices searchKeywordsServices;

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        List<SearchKeywords> keywords = searchKeywordsServices.getAllKeywords(taskId);
        for (int i = 0; i < keywords.size(); i++){
            urls.add("http://shouji.baidu.com/s?wd="+keywords.get(i).getKeyword()+"&data_type=app&f=header_app%40input%40btn_search");
        }

        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
