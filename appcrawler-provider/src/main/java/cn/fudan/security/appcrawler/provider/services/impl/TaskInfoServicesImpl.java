package cn.fudan.security.appcrawler.provider.services.impl;

import cn.fudan.security.appcrawler.provider.dao.TaskInfoMapper;
import cn.fudan.security.appcrawler.provider.entity.TaskInfo;
import cn.fudan.security.appcrawler.provider.services.TaskInfoServices;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qiaoying
 * @date 2018/11/22 20:21
 */
@Component
public class TaskInfoServicesImpl implements TaskInfoServices {

    @Resource
    private TaskInfoMapper taskInfoMapper;

    @Override
    public int insertTaskInfo(TaskInfo taskInfo) {
        return taskInfoMapper.insert(taskInfo);
    }

    @Override
    public TaskInfo getTaskInfoByTaskId(int taskId) {
        return taskInfoMapper.selectByPrimaryKey(taskId);
    }
}
