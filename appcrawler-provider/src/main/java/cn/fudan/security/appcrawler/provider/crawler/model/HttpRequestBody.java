package cn.fudan.security.appcrawler.provider.crawler.model;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qiaoying
 * @date 2018/11/14 21:06
 *
 *  封装了request请求header的一些共同的参数
 * 参数包括body contentType encoding
 * 请求类型分为 JSON XML FORM MULTIPART
 * 静态方法返回不同的请求体
 */
public class HttpRequestBody implements Serializable {

    private static final long serialVersionUID = 5659170945717023595L;

    private static final String JSON = "application/json";

    private static final String XML = "text/xml";

    private static final String FORM = "application/x-www-form-urlencoded";

    private static final String MULTIPART = "multipart/form-data";

    private byte[] body;

    private String contentType;

    private String encoding;

    public HttpRequestBody(){}

    public HttpRequestBody(byte[] body, String contentType, String encoding){
        this.body = body;
        this.contentType = contentType;
        this.encoding = encoding;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public static HttpRequestBody json(String json, String encoding){
        try {
            return new HttpRequestBody(json.getBytes(encoding), JSON, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("illegal encoding " + encoding, e);
        }
    }

    public static HttpRequestBody xml(String json, String encoding){
        try {
            return new HttpRequestBody(json.getBytes(encoding), XML, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("illegal encoding " + encoding, e);
        }
    }

    public static HttpRequestBody custom(byte[] body, String contentType, String encoding){
        return new HttpRequestBody(body, contentType, encoding);
    }

    public static HttpRequestBody form(Map<String, Object> params, String encoding){

        List<NameValuePair> nameValuePairs = new ArrayList<>(params.size());
        for (Map.Entry<String,Object> entry : params.entrySet()){
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }

        try {
            return new HttpRequestBody(URLEncodedUtils.format(nameValuePairs, encoding).getBytes(encoding), FORM, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("illegal encoding " + encoding, e);
        }

    }
}
