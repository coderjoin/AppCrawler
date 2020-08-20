package cn.fudan.security.appcrawler.provider.crawler.processor.all;


import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Site;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;
import cn.fudan.security.appcrawler.provider.crawler.selector.Json;
import cn.fudan.security.appcrawler.provider.entity.Appinfo;
import cn.fudan.security.appcrawler.provider.services.AppInfoServices;
import cn.fudan.security.appcrawler.provider.services.SearchKeywordsServices;
import cn.fudan.security.appcrawler.provider.services.TaskInfoServices;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qiaoying
 * @date 2018/11/20 22:20
 */
@Component
public class YingyongbaoAllPageProcessor implements PageProcessor {
    private Logger logger = LoggerFactory.getLogger(BaiduAllPageProcessor.class);

    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    private Site site = Site.init()
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
            .setRetryTimes(3)
            .addCookie("Cookie", "PHPSESSID=v1t31anh1ku5291ve4pon0e4n59vd7c72uun04922cubicp5m8o1; cs6k_langid=zh_cn; _gat_UA-7728030-4=1; cs6k_2f6c74b2aa33f495a5477460e731939caf1da42f718802b1ac7a1bc32da79a7a=s%3A45%3A%22http%3A%2F%2Fapp.hicloud.com%2Fplugin%2Fappstore%2Fsearch%22%3B; __gahuawei=GA1.2.47233297.1508917370; __gahuawei_gid=GA1.2.614504483.1508917370")
            .setSleepTime(1000)
            .setUseGzip(true)
            .setTimeOut(10000);

    @Resource
    private SearchKeywordsServices searchKeywordsServices;

    @Resource
    private TaskInfoServices taskInfoServices;

    @Resource
    private AppInfoServices appInfoServices;

    @Override
    public void process(Page page) {
        String url = page.getUrl().toString();
        String strTemp = url.replace("http://sj.qq.com/myapp/searchAjax.htm?kw=", "");
        String keyword = strTemp.substring(0,strTemp.indexOf("&pns="));
        searchKeywordsServices.updateSearched(getTaskId(),keyword);
        Json json = page.getJson();
        JSONObject jsonObject = JSON.parseObject(json.toString());
        JSONObject jsonObject1 = jsonObject.getJSONObject("obj");
        JSONArray jsonArray = jsonObject1.getJSONArray("appDetails");
        for (int i = 0; i < jsonArray.size(); ++i) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            Appinfo appInfo = parseFromJSON(jsonObj);
            logger.info("appinfo:{}",appInfo.toString());
            String tableName = taskInfoServices.getTaskInfoByTaskId(getTaskId()).getTablename();
            appInfoServices.insertApp(tableName,appInfo);
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
        return site;
    }
}
