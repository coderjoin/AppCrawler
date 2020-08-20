package cn.fudan.security.appcrawler.provider.urlmaker.top;

import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.model.HttpRequestBody;
import cn.fudan.security.appcrawler.provider.crawler.utils.HttpConstant;
import cn.fudan.security.appcrawler.provider.urlmaker.RequsetMaker;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author qiaoying
 * @date 2018/12/12 14:33
 */
@Component
public class GooglePlayCrawlerUrlsForTop implements RequsetMaker {

    private static final Set<String> CATEGORIES = new LinkedHashSet<>();

    private static final String TOKEN = "tcsByxg4PYEaA_Jcz4DUSU6wZi0:1544603442405";

    static {
//        CATEGORIES.add("FOOD_AND_DRINK");
//        CATEGORIES.add("AUTO_AND_VEHICLES");
//        CATEGORIES.add("MAPS_AND_NAVIGATION");
//        CATEGORIES.add("EVENTS");
//        CATEGORIES.add("HOUSE_AND_HOME");
//        CATEGORIES.add("BEAUTY");
        CATEGORIES.add("DATING");
//        CATEGORIES.add("VIDEO_PLAYERS");
//        CATEGORIES.add("ART_AND_DESIGN");
//        CATEGORIES.add("PARENTING");
//        CATEGORIES.add("ANDROID_WEAR");
//        CATEGORIES.add("BOOKS_AND_REFERENCE");
//        CATEGORIES.add("BUSINESS");
//        CATEGORIES.add("COMICS");
//        CATEGORIES.add("COMMUNICATION");
//        CATEGORIES.add("EDUCATION");
//        CATEGORIES.add("ENTERTAINMENT");
//        CATEGORIES.add("FINANCE");
//        CATEGORIES.add("GAME");
//        CATEGORIES.add("HEALTH_AND_FITNESS");
//        CATEGORIES.add("LIBRARIES_AND_DEMO");
//        CATEGORIES.add("LIFESTYLE");
//        CATEGORIES.add("APP_WALLPAPER");
//        CATEGORIES.add("MEDIA_AND_VIDEO");
//        CATEGORIES.add("MEDICAL");
//        CATEGORIES.add("MUSIC_AND_AUDIO");
//        CATEGORIES.add("NEWS_AND_MAGAZINES");
//        CATEGORIES.add("PERSONALIZATION");
//        CATEGORIES.add("PHOTOGRAPHY");
//        CATEGORIES.add("PRODUCTIVITY");
//        CATEGORIES.add("SHOPPING");
//        CATEGORIES.add("SOCIAL");
//        CATEGORIES.add("SPORTS");
//        CATEGORIES.add("TOOLS");
//        CATEGORIES.add("TRANSPORTATION");
//        CATEGORIES.add("TRAVEL_AND_LOCAL");
//        CATEGORIES.add("WEATHER");
//        CATEGORIES.add("APP_WIDGETS");
    }

    @Override
    public List<Request> getRequests(int taskId) {
        List<Request> requests = new ArrayList<>();
        for (String i :  CATEGORIES){
            for (int j = 0; j < 5000; j = j + 60){
                Request request = new Request();
                request.setUrl("https://play.google.com/store/apps/category/"+i+"/collection/topselling_free?authuser=0");
                //request.setUrl("http://106.15.186.69:33333");
                request.setMethod(HttpConstant.Method.POST);
                System.out.println("j :" + j);
                Map<String, Object> nameValuePair = new HashMap<>();
                nameValuePair.put("start", String.valueOf(j));
                nameValuePair.put("num", String.valueOf(60));
                nameValuePair.put("numChildren", String.valueOf(0));
                nameValuePair.put("cctcss", "square-cover");
                nameValuePair.put("cllayout", "NORMAL");
                nameValuePair.put("ipf", String.valueOf(1));
                nameValuePair.put("xhr", String.valueOf(1));
                nameValuePair.put("token","S5WOu0Tcu98vaUTm2SyE6pCIgUU:1544619145556");
                request.setRequestBody(HttpRequestBody.form(nameValuePair,"UTF-8"));


                requests.add(request);
            }

        }
        System.out.println("requests size : " + requests.size());
        return requests;
    }
}
