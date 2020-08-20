package cn.fudan.security.appcrawler.consumer.entity;

public class Appinfo {
    private String id;

    private String appname;

    private String pkgname;

    private String authorname;

    private String appdowncount;

    private String versionname;

    private String apkmd5;

    private String categoryname;

    private String appsize;

    private Boolean apkdownloaded;

    private String appdownurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname == null ? null : appname.trim();
    }

    public String getPkgname() {
        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname == null ? null : pkgname.trim();
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname == null ? null : authorname.trim();
    }

    public String getAppdowncount() {
        return appdowncount;
    }

    public void setAppdowncount(String appdowncount) {
        this.appdowncount = appdowncount == null ? null : appdowncount.trim();
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname == null ? null : versionname.trim();
    }

    public String getApkmd5() {
        return apkmd5;
    }

    public void setApkmd5(String apkmd5) {
        this.apkmd5 = apkmd5 == null ? null : apkmd5.trim();
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public String getAppsize() {
        return appsize;
    }

    public void setAppsize(String appsize) {
        this.appsize = appsize == null ? null : appsize.trim();
    }

    public Boolean getApkdownloaded() {
        return apkdownloaded;
    }

    public void setApkdownloaded(Boolean apkdownloaded) {
        this.apkdownloaded = apkdownloaded;
    }

    public String getAppdownurl() {
        return appdownurl;
    }

    public void setAppdownurl(String appdownurl) {
        this.appdownurl = appdownurl == null ? null : appdownurl.trim();
    }

    @Override
    public String toString(){
        return "Appinfo{ " +
                "; id: " + id +
                "; appname: " + appname +
                "; authorname: " + authorname +
                "; appdowncount: " + appdowncount +
                "; versionname: " + versionname +
                "; apkmd5: " + apkmd5 +
                "; categoryname: " + categoryname +
                "; appsize: " + appsize +
                "; apkdownloaded: " + apkdownloaded +
                "; appdownurl: " + appdownurl +
                " }";
    }
}