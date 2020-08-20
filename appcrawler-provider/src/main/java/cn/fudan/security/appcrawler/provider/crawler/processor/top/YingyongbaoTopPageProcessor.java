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
 * @date 2018/11/20 10:18
 */
public class YingyongbaoTopPageProcessor implements PageProcessor {

    private Site site = Site.init().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        Json json = page.getJson();
        JSONObject jsonObject = JSON.parseObject(json.toString());
        int count = (int)jsonObject.get("count");
        JSONArray jsonArray = jsonObject.getJSONArray("obj");
        for (int i = 0; i < count; ++i) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            Appinfo tmp = parseFromJSON(jsonObj);
            System.out.println("appinfo: " + tmp.toString());
        }
    }

    private static Appinfo parseFromJSON(JSONObject jsonObj) {
        Appinfo appInfo = new Appinfo();
        appInfo.setId(jsonObj.getString("pkgName"));
        appInfo.setApkmd5(jsonObj.getString("apkMd5"));
        appInfo.setAppname(jsonObj.getString("appName"));
        appInfo.setPkgname(jsonObj.getString("pkgName"));
        appInfo.setAuthorname(jsonObj.getString("authorName"));
        appInfo.setAppsize(Integer.toString((int)jsonObj.get("fileSize")));
        int tmp = (int)jsonObj.get("appDownCount");
        String appDownCount = Integer.toString(tmp);
        appInfo.setAppdowncount(appDownCount);
        appInfo.setAppdownurl(jsonObj.getString("apkUrl"));
        appInfo.setVersionname(jsonObj.getString("versionName"));
        appInfo.setCategoryname(jsonObj.getString("categoryName"));

        return appInfo;
    }

    @Override
    public Site getSite() {
        return  site;
    }
}
