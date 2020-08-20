package cn.fudan.security.appcrawler.provider.crawler;

import cn.fudan.security.appcrawler.provider.crawler.utils.HttpConstant;

import java.util.*;

/**
 * @author qiaoying
 * @date 2018/11/15 16:11
 */
public class Site {

    private int taskId;

    private String domain;

    private String userAgent;

    private Map<String, String> defaultCookies = new LinkedHashMap<>();

    private Map<String, Map<String, String>> cookies = new HashMap<>();

    private String charset;

    private int sleepTime = 5000;

    private int retryTimes = 0;

    private int cycleRetryTimes = 0;

    private int retrySleepTime = 1000;

    private int timeOut = 5000;

    private static final Set<Integer> DEFAULT_STATUS_CODE_SET = new HashSet<>();

    private Set<Integer> acceptStatusCode = DEFAULT_STATUS_CODE_SET;

    private Map<String, String> headers = new HashMap<>();

    private boolean useGzip = true;

    private boolean disableCookieManagement = false;

    static {
        DEFAULT_STATUS_CODE_SET.add(HttpConstant.StatusCode.CODE_200);
    }

    public static Site init(){
        return new Site();
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Site addCookie(String name, String value){
        defaultCookies.put(name, value);
        return this;
    }

    public Site addCookie(String domain, String name, String value){
        if (!cookies.containsKey(domain)){
            cookies.put(domain, new HashMap<String, String>());
        }
        cookies.get(domain).put(name,value);
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Site setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Map<String, String> getDefaultCookies() {
        return defaultCookies;
    }

    public void setDefaultCookies(Map<String, String> defaultCookies) {
        this.defaultCookies = defaultCookies;
    }

    public  Map<String, String> getCookies() {
        return defaultCookies;
    }

    public Map<String,Map<String, String>> getAllCookies() {
        return cookies;
    }

    public void setCookies(Map<String, Map<String, String>> cookies) {
        this.cookies = cookies;
    }

    public String getCharset() {
        return charset;
    }

    public Site setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public Site setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public Site setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }

    public int getCycleRetryTimes() {
        return cycleRetryTimes;
    }

    public void setCycleRetryTimes(int cycleRetryTimes) {
        this.cycleRetryTimes = cycleRetryTimes;
    }

    public int getRetrySleepTime() {
        return retrySleepTime;
    }

    public void setRetrySleepTime(int retrySleepTime) {
        this.retrySleepTime = retrySleepTime;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public Site setTimeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public static Set<Integer> getDefaultStatusCodeSet() {
        return DEFAULT_STATUS_CODE_SET;
    }

    public Set<Integer> getAcceptStatusCode() {
        return acceptStatusCode;
    }

    public void setAcceptStatusCode(Set<Integer> acceptStatusCode) {
        this.acceptStatusCode = acceptStatusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isUseGzip() {
        return useGzip;
    }

    public Site setUseGzip(boolean useGzip) {
        this.useGzip = useGzip;
        return this;
    }

    public boolean isDisableCookieManagement() {
        return disableCookieManagement;
    }

    public void setDisableCookieManagement(boolean disableCookieManagement) {
        this.disableCookieManagement = disableCookieManagement;
    }

    public Task toTask(){
        return new Task(){

            @Override
            public String getCrawlerId() {
                String id = String.valueOf(Site.this.getTaskId());
                if (id.isEmpty()){
                    id = UUID.randomUUID().toString();
                }
                return id;
            }

            @Override
            public Site getSite() {
                return Site.this;
            }
        };
    }

    @Override
    public String toString() {
        return "Site{" +
                "domain='" + domain + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", cookies=" + defaultCookies +
                ", charset='" + charset + '\'' +
                ", sleepTime=" + sleepTime +
                ", retryTimes=" + retryTimes +
                ", cycleRetryTimes=" + cycleRetryTimes +
                ", timeOut=" + timeOut +
                ", acceptStatCode=" + acceptStatusCode +
                ", headers=" + headers +
                '}';
    }

}
