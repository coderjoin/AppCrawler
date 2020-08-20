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
 * @date 2018/11/19 10:27
 */
@Component
public class HuaweiCrawlerUrlsForTop implements RequsetMaker {


    private static final HashMap<Integer, String> CATEGORIES = new HashMap<>();

    private static final HashMap<Integer, String> SORT = new HashMap<>();

    static {
        CATEGORIES.put(23, "影音娱乐");
        CATEGORIES.put(24, "实用工具");
        CATEGORIES.put(26, "社交通讯");
        CATEGORIES.put(30, "教育");
        CATEGORIES.put(345, "新闻阅读");
        CATEGORIES.put(33, "拍摄美化");
        CATEGORIES.put(359, "美食");
        CATEGORIES.put(28, "出行导航");
        CATEGORIES.put(361, "旅游住宿");
        CATEGORIES.put(358, "购物比价");
        CATEGORIES.put(362, "商务");
        CATEGORIES.put(363, "儿童");
        CATEGORIES.put(25, "金融理财");
        CATEGORIES.put(31, "运动健康");
        CATEGORIES.put(27, "便捷生活");
        CATEGORIES.put(360, "汽车");
        CATEGORIES.put(29, "主题个性");

        SORT.put(0, "热门推荐");
        SORT.put(2, "综合评分");
        SORT.put(1, "更新时间");
    }

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        for (int cid : CATEGORIES.keySet()){
            for (int sid : SORT.keySet()){
               String url = "http://appstore.huawei.com/soft/list_" + cid + "_" + sid;
               urls.add(url);
            }
        }
        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
