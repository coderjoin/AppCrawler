package cn.fudan.security.appcrawler.consumer.task;


import cn.fudan.security.appcrawler.consumer.entity.TaskInfo;
import cn.fudan.security.appcrawler.consumer.services.AppInfoServices;
import cn.fudan.security.appcrawler.consumer.services.SearchKeywordsServices;
import cn.fudan.security.appcrawler.consumer.services.TaskInfoServices;
import cn.fudan.security.appcrawler.consumer.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qiaoying
 * @date 2018/9/27 10:02
 */
@Component
public class TaskManager {

    @Resource
    private AppInfoServices appInfoServices;

    @Autowired
    private TaskInfoServices taskInfoServices;

    @Autowired
    private SearchKeywordsServices searchKeywordsServices;

    public  int createCrawlerTask(String market, String creator, String scope){

        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setMarket(market);
        taskInfo.setTaskcreator(creator);
        taskInfo.setScope(scope);
        taskInfo.setStatus("ready");
        taskInfo.setCreatedate(new Date());
        String dateStr = DateHelper.getCurrentDayStr();
        String tableName = "apps_"+market+"_"+dateStr+"_"+scope;
        System.out.println("创建的表：" + tableName);
        appInfoServices.createTable(tableName);
        taskInfo.setTablename(tableName);
        taskInfoServices.insertTaskInfo(taskInfo);
        int taskId = taskInfoServices.getTaskInfoByTableName(tableName).getTaskid();
        if ("all".equals(scope)){
            searchKeywordsServices.createTable(taskId);
        }
        return taskId;

    }

}
