package cn.fudan.security.appcrawler.provider.entity;

import java.util.Date;

public class TaskInfo {
    private Integer taskid;

    private String market;

    private Date createdate;

    private String taskcreator;

    private String scope;

    private String status;

    private String tablename;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getTaskcreator() {
        return taskcreator;
    }

    public void setTaskcreator(String taskcreator) {
        this.taskcreator = taskcreator == null ? null : taskcreator.trim();
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }
}