package cn.fudan.security.appcrawler.provider.crawler.processor.top;

import cn.fudan.security.appcrawler.consumer.entity.Appinfo;
import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;

import cn.fudan.security.appcrawler.provider.crawler.selector.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * @author qiaoying
 * @date 2018/11/20 10:19
 */
public class MiTopPageProcessor implements PageProcessor {

    private Site site = Site.init().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Json json = page.getJson();
        JSONObject jsonObject = JSON.parseObject(json.toString());
        int count =(int)jsonObject.get("count");
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < 30; ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Appinfo tmp = parseFromJSON(jsonObj);
                System.out.println("appinfo: " + tmp.toString());
            }
        }catch (Exception e){

        }
    }

    private static Appinfo parseFromJSON(JSONObject jsonObj) {
        Appinfo appInfo = new Appinfo();
        int appid = (int)jsonObj.get("appId");
        String id = Integer.toString(appid);
        appInfo.setId(id);
        //appInfo.setApkMd5(jsonObj.getString("apkMd5"));
        appInfo.setAppname(jsonObj.getString("displayName"));
        appInfo.setPkgname(jsonObj.getString("packageName"));
//        int tmp = jsonObj.getInt("appDownCount");
//        String appDownCount = Integer.toString(tmp);
//        appInfo.setAppDownCount(appDownCount);
        appInfo.setAppdownurl("http://app.mi.com/download/"+id);
        //appInfo.setVersionName(jsonObj.getString("versionName"));
        appInfo.setCategoryname(jsonObj.getString("level1CategoryName"));

        return appInfo;
    }

    @Override
    public Site getSite() {
        return  site;
    }
}
