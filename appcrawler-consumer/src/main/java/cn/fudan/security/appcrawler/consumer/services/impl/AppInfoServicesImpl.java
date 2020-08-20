package cn.fudan.security.appcrawler.consumer.services.impl;

import cn.fudan.security.appcrawler.consumer.dao.AppinfoMapper;
import cn.fudan.security.appcrawler.consumer.services.AppInfoServices;
import cn.fudan.security.appcrawler.consumer.utils.DateHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qiaoying
 * @date 2018/9/29 11:04
 *
 */
@Service("appInfoServices")
public class AppInfoServicesImpl implements AppInfoServices {

    @Resource
    private AppinfoMapper appinfoMapper;

    @Override
    public int createTable(String tableName) {
        return appinfoMapper.createTable(tableName);
    }
}
