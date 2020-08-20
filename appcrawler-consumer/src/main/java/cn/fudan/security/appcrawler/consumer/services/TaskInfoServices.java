package cn.fudan.security.appcrawler.consumer.services;

import cn.fudan.security.appcrawler.consumer.entity.TaskInfo;

/**
 * @author qiaoying
 * @date 2018/11/22 20:21
 */
public interface TaskInfoServices {

    int insertTaskInfo(TaskInfo taskInfo);

    TaskInfo getTaskInfoByTaskId(int taskId);

    TaskInfo getTaskInfoByTableName(String tableName);
}
