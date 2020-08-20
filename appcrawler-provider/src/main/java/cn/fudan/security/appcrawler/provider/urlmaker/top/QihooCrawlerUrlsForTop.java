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
 * @date 2018/11/19 10:28
 */
@Component
public class QihooCrawlerUrlsForTop implements RequsetMaker {

    private static final HashMap<Integer, String> CATEGORIES = new HashMap<Integer, String>();

    private static final HashMap<Integer, String> GAMES = new HashMap<Integer, String>();

    static {
        CATEGORIES.put(11, "系统安全");
        CATEGORIES.put(12, "通讯社交");
        CATEGORIES.put(14, "影音技术");
        CATEGORIES.put(15, "新闻阅读");
        CATEGORIES.put(16, "生活休闲");
        CATEGORIES.put(17, "办公商务");
        CATEGORIES.put(18, "主题壁纸");
        CATEGORIES.put(102139, "金融理财");
        CATEGORIES.put(102228, "摄影摄像");
        CATEGORIES.put(102230, "购物优惠");
        CATEGORIES.put(102231, "地图旅游");
        CATEGORIES.put(102232, "教育学习");
        CATEGORIES.put(102233, "健康医疗");

        GAMES.put(19, "休闲益智");
        GAMES.put(20, "动作冒险");
        GAMES.put(51, "体育竞速");
        GAMES.put(52, "飞行射击");
        GAMES.put(53, "经营策略");
        GAMES.put(54, "棋牌天地");
        GAMES.put(100451, "网络游戏");
        GAMES.put(101587, "角色扮演");
        GAMES.put(102238, "儿童游戏");
    }

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        for (int cid : CATEGORIES.keySet()){
            int page = 1, finishPage = 50;
            while (page <= finishPage){
                String url = "http://zhushou.360.cn/list/index/cid/" + cid + "/order/download/?page=" + page;
                urls.add(url);
                ++ page;
            }
        }
        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
