package cn.fudan.security.appcrawler.provider.urlmaker.all;


import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.utils.UrlUtils;
import cn.fudan.security.appcrawler.provider.entity.SearchKeywords;
import cn.fudan.security.appcrawler.provider.services.SearchKeywordsServices;
import cn.fudan.security.appcrawler.provider.urlmaker.RequsetMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/21 9:51
 */
@Component
public class YingyongbaoCrawlerUrlsForAll implements RequsetMaker {

    private static final HashMap<Integer, String> URL = new HashMap<>();

    static {
        URL.put(0,"MTA");
        URL.put(1,"MjA");
        URL.put(2,"MzA");
        URL.put(3,"NDA");
        URL.put(4,"NTA");
    }

    @Autowired
    private SearchKeywordsServices searchKeywordsServices;

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        List<SearchKeywords> keywords = searchKeywordsServices.getAllKeywords(taskId);
        for (int i = 0; i < keywords.size(); i++){
            for (int j = 0; j < 5; j++){
                urls.add("http://sj.qq.com/myapp/searchAjax.htm?kw="+keywords.get(i).getKeyword()+"&pns="+URL.get(j));
            }
        }
        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
