package cn.fudan.security.appcrawler.provider.urlmaker;


import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.entity.TaskInfo;
import cn.fudan.security.appcrawler.provider.services.TaskInfoServices;
import cn.fudan.security.appcrawler.provider.urlmaker.all.*;
import cn.fudan.security.appcrawler.provider.urlmaker.top.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/19 10:10
 */
@Component
public class RequestsFactory {

    private static final String BAIDU = "baidu";
    private static final String ANZHI = "anzhi";
    private static final String HUAWEI = "huawei";
    private static final String MI = "mi";
    private static final String QIHOO = "qihoo";
    private static final String YINGYONGBAO = "yingyongbao";
    private static final String GOOGLEPLAY = "googleplay";

    private static final String TOP = "top";
    private static final String ALL = "all";

    @Autowired
    private AnzhiCrawlerUrlsForTop anzhiCrawlerUrlsForTop;

    @Autowired
    private BaiduCrawlerUrlsForAll baiduCrawlerUrlsForAll;

    @Autowired
    private BaiduCrawlerUrlsForTop baiduCrawlerUrlsForTop;

    @Autowired
    private HuaweiCrawlerUrlsForTop huaweiCrawlerUrlsForTop;

    @Autowired
    private HuaweiCrawlerUrlsForAll huaweiCrawlerUrlsForAll;

    @Autowired
    private MiCrawlerUrlsForTop miCrawlerUrlsForTop;

    @Autowired
    private MiCrawlerUrlsForAll miCrawlerUrlsForAll;

    @Autowired
    private QihooCrawlerUrlsForTop qihooCrawlerUrlsForTop;

    @Autowired
    private QihooCrawlerUrlsForAll qihooCrawlerUrlsForAll;

    @Autowired
    private YingyongbaoCrawlerUrlsForTop yingyongbaoCrawlerUrlsForTop;

    @Autowired
    private YingyongbaoCrawlerUrlsForAll yingyongbaoCrawlerUrlsForAll;

    @Autowired
    private GooglePlayCrawlerUrlsForTop googlePlayCrawlerUrlsForTop;

    @Autowired
    private TaskInfoServices taskInfoServices;

    public  List<Request> getRequests(int taskId){
        List<Request> urls = new ArrayList<>();
        TaskInfo taskInfo = taskInfoServices.getTaskInfoByTaskId(taskId);
        String marketName = taskInfo.getMarket();
        String scope = taskInfo.getScope();
        if (marketName.equals(BAIDU) && scope.equals(TOP)){
            urls = baiduCrawlerUrlsForTop.getRequests(taskId);
        }

        if (marketName.equals(BAIDU) && scope.equals(ALL)){
            urls = baiduCrawlerUrlsForAll.getRequests(taskId);
        }

        if (marketName.equals(ANZHI) && scope.equals(TOP)){
            urls = anzhiCrawlerUrlsForTop.getRequests(taskId);
        }

        if (marketName.equals(ANZHI) && scope.equals(ALL)){

        }

        if (marketName.equals(HUAWEI) && scope.equals(TOP)){
            urls = huaweiCrawlerUrlsForTop.getRequests(taskId);
        }

        if (marketName.equals(HUAWEI) && scope.equals(ALL)){
            urls = huaweiCrawlerUrlsForAll.getRequests(taskId);
        }

        if (marketName.equals(MI) && scope.equals(TOP)){
            urls = miCrawlerUrlsForTop.getRequests(taskId);
        }

        if (marketName.equals(MI) && scope.equals(ALL)){
            urls = miCrawlerUrlsForAll.getRequests(taskId);
        }

        if (marketName.equals(QIHOO) && scope.equals(TOP)){
            urls = qihooCrawlerUrlsForTop.getRequests(taskId);
        }

        if (marketName.equals(QIHOO) && scope.equals(ALL)){
            urls = qihooCrawlerUrlsForAll.getRequests(taskId);
        }

        if (marketName.equals(YINGYONGBAO) && scope.equals(TOP)){
            urls = yingyongbaoCrawlerUrlsForTop.getRequests(taskId);
        }

        if (marketName.equals(YINGYONGBAO) && scope.equals(ALL)){
            urls = yingyongbaoCrawlerUrlsForAll.getRequests(taskId);
        }

        if (marketName.equals(GOOGLEPLAY) && scope.equals(TOP)){
            urls = googlePlayCrawlerUrlsForTop.getRequests(taskId);
        }

        return urls;
    }
}
