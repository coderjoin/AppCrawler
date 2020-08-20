package cn.fudan.security.appcrawler.provider.entity;

public class SearchKeywords {
    private Integer id;

    private String keyword;

    private Boolean issearched;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Boolean getIssearched() {
        return issearched;
    }

    public void setIssearched(Boolean issearched) {
        this.issearched = issearched;
    }
}