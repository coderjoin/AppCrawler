package cn.fudan.security.appcrawler.provider.urlmaker.top;

import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.utils.UrlUtils;
import cn.fudan.security.appcrawler.provider.urlmaker.RequsetMaker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/19 9:54
 */
@Component
public class BaiduCrawlerUrlsForTop implements RequsetMaker {


    private static final HashMap<Integer, String> CATEGORIES = new HashMap<>();

    static {
        CATEGORIES.put(501, "系统工具");
        CATEGORIES.put(502, "主题壁纸");
        CATEGORIES.put(503, "社交通讯");
        CATEGORIES.put(504, "生活实用");
        CATEGORIES.put(505, "资讯阅读");
        CATEGORIES.put(506, "影音播放");
        CATEGORIES.put(508, "拍摄美化");
        CATEGORIES.put(507, "办公学习");
        CATEGORIES.put(509, "旅游出行");
        CATEGORIES.put(510, "理财购物");
    }

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        for (int id : CATEGORIES.keySet()){
            int page = 1;
            while (page != 9){
                String url = "http://shouji.baidu.com/software/" + id + "/list_" + page + ".html";
                urls.add(url);
                ++ page;
            }
        }
        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
