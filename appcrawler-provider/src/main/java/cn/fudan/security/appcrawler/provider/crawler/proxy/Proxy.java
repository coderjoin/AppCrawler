package cn.fudan.security.appcrawler.provider.crawler.proxy;

/**
 * @author qiaoying
 * @date 2018/11/15 18:27
 */
public class Proxy {

    private String host;

    private int port;

    private String username;

    private String password;

    public Proxy(String host, int port){
        this.host = host;
        this.port = port;
    }

    public Proxy(String host, int port, String username, String password){
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Proxy{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
