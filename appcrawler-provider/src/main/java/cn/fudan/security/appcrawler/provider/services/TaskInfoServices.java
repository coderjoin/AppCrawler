package cn.fudan.security.appcrawler.provider.services;

import cn.fudan.security.appcrawler.provider.entity.TaskInfo;

/**
 * @author qiaoying
 * @date 2018/11/22 20:21
 */
public interface TaskInfoServices {

    int insertTaskInfo(TaskInfo taskInfo);

    TaskInfo getTaskInfoByTaskId(int taskId);
}
