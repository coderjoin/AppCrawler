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
public class YingyongbaoCrawlerUrlsForTop implements RequsetMaker {

    private static final HashMap<Integer, String> CATEGORIES = new HashMap<Integer, String>();

    private static final HashMap<Integer, String> GAMES = new HashMap<Integer, String>();

    private static final HashMap<Integer, String> url = new HashMap<>();

    static {
        CATEGORIES.put(100, "children");
        CATEGORIES.put(101, "music");
        CATEGORIES.put(102, "reading");
        CATEGORIES.put(103, "video");
        CATEGORIES.put(104, "camera");
        CATEGORIES.put(105, "amusement");
        CATEGORIES.put(106, "social");
        CATEGORIES.put(107, "life");
        CATEGORIES.put(108, "travel");
        CATEGORIES.put(109, "health");
        CATEGORIES.put(110, "news");
        CATEGORIES.put(111, "education");
        CATEGORIES.put(112, "navigation");
        CATEGORIES.put(113, "office");
        CATEGORIES.put(114, "financing");
        CATEGORIES.put(115, "tool");
        CATEGORIES.put(116, "communication");
        CATEGORIES.put(117, "system");
        CATEGORIES.put(118, "security");
        CATEGORIES.put(119, "desktop_wallpaper");
        CATEGORIES.put(122, "shopping");

        // games
        GAMES.put(121, "network");
        GAMES.put(144, "action");
        GAMES.put(146, "");
        GAMES.put(147, "casual");
        GAMES.put(148, "");
        GAMES.put(149, "");
        GAMES.put(151, "");
        GAMES.put(153, "");

        url.put(0,"MTA");
        url.put(1,"MjA");
        url.put(2,"MzA");
        url.put(3,"NDA");
        url.put(4,"NTA");
    }

    @Override
    public List<Request> getRequests(int taskId) {
        List<String> urls = new ArrayList<>();
        for (int cid : CATEGORIES.keySet()){
            int pageContext = 0;
            while (pageContext != 4000){
                String url = "http://sj.qq.com/myapp/cate/appList.htm?orgame=1&categoryId=" + cid + "&pageSize=20&pageContext=" +pageContext;
                urls.add(url);
                pageContext = pageContext + 20;
            }
        }
        List<Request> requests = UrlUtils.convertToRequests(urls);
        return requests;
    }
}
