package cn.fudan.security.appcrawler.provider.services.impl;

import cn.fudan.security.appcrawler.provider.dao.AppinfoMapper;
import cn.fudan.security.appcrawler.provider.entity.Appinfo;
import cn.fudan.security.appcrawler.provider.services.AppInfoServices;
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
    public int insertApp(String tableName, Appinfo appinfo) {
        return appinfoMapper.insertApp(tableName,appinfo);
    }
}
