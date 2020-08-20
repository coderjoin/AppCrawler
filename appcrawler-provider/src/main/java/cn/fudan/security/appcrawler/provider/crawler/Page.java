package cn.fudan.security.appcrawler.provider.crawler;

import cn.fudan.security.appcrawler.provider.crawler.selector.Html;
import cn.fudan.security.appcrawler.provider.crawler.selector.Json;
import cn.fudan.security.appcrawler.provider.crawler.selector.Selectable;
import cn.fudan.security.appcrawler.provider.crawler.utils.HttpConstant;
import cn.fudan.security.appcrawler.provider.crawler.utils.UrlUtils;
import org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qiaoying
 * @date 2018/11/14 20:55
 */
public class Page {

    private int taskId;

    private Request request;

    private ResultItems resultItems = new ResultItems();

    private Html html;

    private Json json;

    private String rawText;

    private Selectable url;

    private Map<String, List<String>> headers;

    private int statusCode = HttpConstant.StatusCode.CODE_200;

    private boolean downloadSuccess = true;

    private byte[] bytes;

    private List<Request> targetRequests = new ArrayList<>();

    private String charset;

    public Request getRequest() {
        return request;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * 将结果中标明request请求
     * @param request
     */

    public void setRequest(Request request) {
        this.request = request;
        this.resultItems.setRequest(request);
    }

    public ResultItems getResultItems() {
        return resultItems;
    }

    public void setResultItems(ResultItems resultItems) {
        this.resultItems = resultItems;
    }

    public Html getHtml() {
        if (html == null){
            html = new Html(rawText, request.getUrl());
        }
        return html;
    }

    public void setHtml(Html html) {
        this.html = html;
    }

    public Json getJson() {
        if (json == null){
            json = new Json(rawText);
        }
        return json;
    }

    public void setJson(Json json) {
        this.json = json;
    }

    public String getRawText() {
        return rawText;
    }

    public Page setRawText(String rawText) {
        this.rawText = rawText;
        return this;
    }

    public Selectable getUrl() {
        return url;
    }

    public void setUrl(Selectable url) {
        this.url = url;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isDownloadSuccess() {
        return downloadSuccess;
    }

    public void setDownloadSuccess(boolean downloadSuccess) {
        this.downloadSuccess = downloadSuccess;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public List<Request> getTargetRequests() {
        return targetRequests;
    }


    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public static Page fail(){
        Page page = new Page();
        page.setDownloadSuccess(false);
        return page;
    }

    public Page setSkip(boolean skip){
        resultItems.setSkip(skip);
        return this;
    }

    public void putField(String key, Object field){
        resultItems.put(key, field);
    }

    /**
     * 添加方法，增加request
     */
    public void addTargetRequests(List<String> requests){
        for (String s : requests){
            if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")){
                continue;
            }
            s = UrlUtils.canonicalizeUrl(s, url.toString());
            targetRequests.add(new Request(s));
        }
    }

    public void addTargetRequests(List<String> requests, long priority){
        for (String s : requests){
            if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")){
                continue;
            }
            s = UrlUtils.canonicalizeUrl(s, url.toString());
            targetRequests.add(new Request(s).setPriority(priority));
        }
    }

    public void addTargetRequest(String requestString) {
        if (StringUtils.isBlank(requestString) || requestString.equals("#")) {
            return;
        }
        requestString = UrlUtils.canonicalizeUrl(requestString, url.toString());
        targetRequests.add(new Request(requestString));
    }

    public void addTargetRequest(Request request) {
        targetRequests.add(request);
    }

    @Override
    public String toString() {
        return "Page{" +
                "request=" + request +
                ", resultItems=" + resultItems +
                ", html=" + html +
                ", json=" + json +
                ", rawText='" + rawText + '\'' +
                ", url=" + url +
                ", headers=" + headers +
                ", statusCode=" + statusCode +
                ", downloadSuccess=" + downloadSuccess +
                ", targetRequests=" + targetRequests +
                ", charset='" + charset + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }


}
