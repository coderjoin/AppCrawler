package cn.fudan.security.appcrawler.provider.crawler.downloader;

import cn.fudan.security.appcrawler.provider.crawler.Page;
import cn.fudan.security.appcrawler.provider.crawler.Request;
import cn.fudan.security.appcrawler.provider.crawler.Task;

/**
 * @author qiaoying
 * @date 2018/11/14 22:32
 */
public interface Downloader {

    Page download(Request request, Task task);

    void setThread(int threadNum);

}
