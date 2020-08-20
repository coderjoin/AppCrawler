package cn.fudan.security.appcrawler.provider.crawler;

import cn.fudan.security.appcrawler.provider.crawler.model.HttpRequestBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiaoying
 * @date 2018/11/14 21:01
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 2062192774891352043L;

    public static final String CYCLE_TRIED_TIMES = "_cycle_tried_times";

    private String url;

    private String method;

    private HttpRequestBody requestBody;

    private Map<String, Object> extras;

    private Map<String, String> cookies = new HashMap<>();

    private Map<String, String> headers = new HashMap<>();

    private long priority;

    private boolean binaryContent = false;

    private String charset;

    public Request(){}

    public Request(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Request setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Request setMethod(String method) {
        this.method = method;
        return this;
    }

    public HttpRequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(HttpRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public Object getExtra(String key) {
        if (extras == null){
            return null;
        }
        return extras.get(key);
    }

    public Request putExtra(String key, Object value) {
        if (extras == null){
            extras = new HashMap<String, Object>();
        }
        extras.put(key, value);
        return this;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public Request setExtras(Map<String, Object> extras) {
        this.extras = extras;
        return this;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public Request setCookie(String name, String value) {
        cookies.put(name, value);
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Request setHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public long getPriority() {
        return priority;
    }

    public Request setPriority(long priority) {
        this.priority = priority;
        return this;
    }

    public boolean isBinaryContent() {
        return binaryContent;
    }

    public Request setBinaryContent(boolean binaryContent) {
        this.binaryContent = binaryContent;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (url != null ? !url.equals(request.url) : request.url != null) return false;
        return method != null ? method.equals(request.method) : request.method == null;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", extras=" + extras +
                ", priority=" + priority +
                ", headers=" + headers +
                ", cookies="+ cookies+
                '}';
    }
}
