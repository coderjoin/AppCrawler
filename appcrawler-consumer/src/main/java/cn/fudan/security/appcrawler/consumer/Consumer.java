package cn.fudan.security.appcrawler.consumer;

import cn.fudan.security.appcrawler.AppcrawlerService;
import cn.fudan.security.appcrawler.consumer.services.AppInfoServices;
import cn.fudan.security.appcrawler.consumer.task.TaskManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author qiaoying
 * @date 2018/9/21 10:13
 */
public class Consumer {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
//        AppcrawlerService appcrawlerService = context.getBean(AppcrawlerService.class);
//        System.out.println("consumer");
        //System.out.println(appcrawlerService.sayHello("qiaoying"));

        TaskManager taskManager = context.getBean(TaskManager.class);
        int taskId = taskManager.createCrawlerTask("baidu","qiaoying","top");
//        AppcrawlerService appcrawlerService = context.getBean(AppcrawlerService.class);
//        appcrawlerService.startCrawler(taskId);
        System.out.println("taskId:" + taskId);

    }
}
