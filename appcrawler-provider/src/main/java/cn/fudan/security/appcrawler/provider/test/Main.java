package cn.fudan.security.appcrawler.provider.test;

import cn.fudan.security.appcrawler.provider.crawler.Crawler;
import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.processor.top.BaiduTopPageProcessor;
import cn.fudan.security.appcrawler.provider.crawler.processor.top.GooglePlayTopPageProcessor;
import cn.fudan.security.appcrawler.provider.crawler.utils.HttpConstant;
import cn.fudan.security.appcrawler.provider.urlmaker.RequestsFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaoying
 * @date 2018/11/16 13:33
 */
public class Main {



    public static void main(String[] args){
//        BaiduCrawlerUrlsForTop baiduCrawlerUrlsForTop = new BaiduCrawlerUrlsForTop();
//        List<String> urls =  baiduCrawlerUrlsForTop.getUrls("baidu","top");
//        System.out.println(urls.size());
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        RequestsFactory requestsFactory = context.getBean(RequestsFactory.class);
        List<Request> requests = requestsFactory.getRequests(4);
        System.out.println(requests.size());

        /**
         * Test Google play top
         *
         */
//        GooglePlayTopPageProcessor googlePlayTopPageProcessor = context.getBean(GooglePlayTopPageProcessor.class);
//        googlePlayTopPageProcessor.setTaskId(18);
//        Crawler.createWithTask(googlePlayTopPageProcessor).startRequest(requests).thread(6).run();

        /**
         * Test Baidu top
         *
         */
        BaiduTopPageProcessor baiduTopPageProcessor = context.getBean(BaiduTopPageProcessor.class);
        baiduTopPageProcessor.setTaskId(4);
        Crawler.createWithTask(baiduTopPageProcessor).startRequest(requests).thread(6).run();

        /**
         * Test Baidu all
         *
         */
//        BaiduAllPageProcessor baiduAllPageProcessor = context.getBean(BaiduAllPageProcessor.class);
//        baiduAllPageProcessor.setTaskId(16);
//        Crawler.createWithTask(baiduAllPageProcessor).startUrls(urls).thread(6).run();

        /**
         * Test Huawei all
         *
         */
//        HuaweiAllPageProcessor huaweiAllPageProcessor = context.getBean(HuaweiAllPageProcessor.class);
//        huaweiAllPageProcessor.setTaskId(7);
//        Crawler.createWithTask(huaweiAllPageProcessor).startUrls(urls).thread(2).run();

        /**
         * Test Mi all
         */

//        MiAllPageProcessor miAllPageProcessor = context.getBean(MiAllPageProcessor.class);
//        miAllPageProcessor.setTaskId(8);
//        Crawler.createWithTask(miAllPageProcessor).startUrls(urls).thread(2).run();

        /**
         * Test Qihoo all
         */
//        QihooAllPageProcessor qihooAllPageProcessor = context.getBean(QihooAllPageProcessor.class);
//        qihooAllPageProcessor.setTaskId(9);
//        Crawler.createWithTask(qihooAllPageProcessor).startUrls(urls).thread(2).run();

        /**
         * Test yingyongbao all
         */
//        YingyongbaoAllPageProcessor yingyongbaoAllPageProcessor = context.getBean(YingyongbaoAllPageProcessor.class);
//        yingyongbaoAllPageProcessor.setTaskId(10);
//        Crawler.createWithTask(yingyongbaoAllPageProcessor).startUrls(urls).thread(2).run();

    }

}
