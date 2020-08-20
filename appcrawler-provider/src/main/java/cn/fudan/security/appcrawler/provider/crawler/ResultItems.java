package cn.fudan.security.appcrawler.provider.crawler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qiaoying
 * @date 2018/11/15 9:41
 */
public class ResultItems {

    private Map<String, Object> fields = new LinkedHashMap<>();

    private Request request;

    private boolean skip;

    public <T> T get(String key){
        Object o = fields.get(key);
        if (o == null){
            return null;
        }
        return (T)fields.get(key);
    }

    public Map<String, Object> getAll(){
        return fields;
    }

    public <T> ResultItems put(String key, T value){
        fields.put(key, value);
        return this;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    @Override
    public String toString(){
        return "ResultItems{" +
                "fields=" + fields +
                ", request=" + request +
                ", skip=" + skip +
                '}';
    }
}
