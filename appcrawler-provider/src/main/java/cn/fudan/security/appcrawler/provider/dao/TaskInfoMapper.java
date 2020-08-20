package cn.fudan.security.appcrawler.provider.dao;

import cn.fudan.security.appcrawler.provider.entity.TaskInfo;
import cn.fudan.security.appcrawler.provider.entity.TaskInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskInfoMapper {
    int countByExample(TaskInfoExample example);

    int deleteByExample(TaskInfoExample example);

    int deleteByPrimaryKey(Integer taskid);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    List<TaskInfo> selectByExample(TaskInfoExample example);

    TaskInfo selectByPrimaryKey(Integer taskid);

    int updateByExampleSelective(@Param("record") TaskInfo record, @Param("example") TaskInfoExample example);

    int updateByExample(@Param("record") TaskInfo record, @Param("example") TaskInfoExample example);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);


}