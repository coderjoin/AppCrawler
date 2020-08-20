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
public class MiCrawlerUrlsForTop implements RequsetMaker {

    private static final HashMap<Integer, String> CATEGORIES = new HashMap<>();

    private static final HashMap<Integer, String> SORT = new HashMap<>();

    static {
        CATEGORIES.put(27, "影音视听");
        CATEGORIES.put(5, "实用工具");
        CATEGORIES.put(2, "聊天社交");
        CATEGORIES.put(12, "学习教育");
        CATEGORIES.put(7, "图书阅读");
        CATEGORIES.put(4, "居家生活");
        CATEGORIES.put(3, "旅行交通");
        CATEGORIES.put(9, "时尚购物");
        CATEGORIES.put(6, "摄影摄像");
        CATEGORIES.put(11, "新闻资讯");
        CATEGORIES.put(1, "金融理财");
        CATEGORIES.put(14, "医疗健康");
        CATEGORIES.put(10, "效率办公");
        CATEGORIES.put(8, "体育运动");
        CATEGORIES.put(13, "娱乐消遣");

        SORT.put(0, "热门推荐");
        SORT.put(2, "综合评分");
        SORT.put(1, "更新时间");
    }

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        for (int cid : CATEGORIES.keySet()){
            int page = 0;
            while (page != 100){
                String url = "http://app.mi.com/categotyAllListApi?page="+page+"&categoryId="+cid+"&pageSize=30";
                urls.add(url);
                ++ page;
            }
        }
        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
