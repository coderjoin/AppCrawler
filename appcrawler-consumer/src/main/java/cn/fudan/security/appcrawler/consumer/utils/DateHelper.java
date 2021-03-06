package cn.fudan.security.appcrawler.consumer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qiaoying
 * @date 2018/9/30 9:41
 */
public class DateHelper {
    public static String getCurrentTimeStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getCurrentDayStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
