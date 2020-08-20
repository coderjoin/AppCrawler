package cn.fudan.security.appcrawler.provider.services;

import cn.fudan.security.appcrawler.provider.entity.Appinfo;

/**
 * @author qiaoying
 * @date 2018/9/29 11:04
 */
public interface AppInfoServices {

    int insertApp(String tableName, Appinfo appinfo);

}
