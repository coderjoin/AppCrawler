package cn.fudan.security.appcrawler.consumer.entity;

import java.util.ArrayList;
import java.util.List;

public class AppinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAppnameIsNull() {
            addCriterion("appName is null");
            return (Criteria) this;
        }

        public Criteria andAppnameIsNotNull() {
            addCriterion("appName is not null");
            return (Criteria) this;
        }

        public Criteria andAppnameEqualTo(String value) {
            addCriterion("appName =", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameNotEqualTo(String value) {
            addCriterion("appName <>", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameGreaterThan(String value) {
            addCriterion("appName >", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameGreaterThanOrEqualTo(String value) {
            addCriterion("appName >=", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameLessThan(String value) {
            addCriterion("appName <", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameLessThanOrEqualTo(String value) {
            addCriterion("appName <=", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameLike(String value) {
            addCriterion("appName like", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameNotLike(String value) {
            addCriterion("appName not like", value, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameIn(List<String> values) {
            addCriterion("appName in", values, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameNotIn(List<String> values) {
            addCriterion("appName not in", values, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameBetween(String value1, String value2) {
            addCriterion("appName between", value1, value2, "appname");
            return (Criteria) this;
        }

        public Criteria andAppnameNotBetween(String value1, String value2) {
            addCriterion("appName not between", value1, value2, "appname");
            return (Criteria) this;
        }

        public Criteria andPkgnameIsNull() {
            addCriterion("pkgName is null");
            return (Criteria) this;
        }

        public Criteria andPkgnameIsNotNull() {
            addCriterion("pkgName is not null");
            return (Criteria) this;
        }

        public Criteria andPkgnameEqualTo(String value) {
            addCriterion("pkgName =", value, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameNotEqualTo(String value) {
            addCriterion("pkgName <>", value, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameGreaterThan(String value) {
            addCriterion("pkgName >", value, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameGreaterThanOrEqualTo(String value) {
            addCriterion("pkgName >=", value, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameLessThan(String value) {
            addCriterion("pkgName <", value, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameLessThanOrEqualTo(String value) {
            addCriterion("pkgName <=", value, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameLike(String value) {
            addCriterion("pkgName like", value, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameNotLike(String value) {
            addCriterion("pkgName not like", value, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameIn(List<String> values) {
            addCriterion("pkgName in", values, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameNotIn(List<String> values) {
            addCriterion("pkgName not in", values, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameBetween(String value1, String value2) {
            addCriterion("pkgName between", value1, value2, "pkgname");
            return (Criteria) this;
        }

        public Criteria andPkgnameNotBetween(String value1, String value2) {
            addCriterion("pkgName not between", value1, value2, "pkgname");
            return (Criteria) this;
        }

        public Criteria andAuthornameIsNull() {
            addCriterion("authorName is null");
            return (Criteria) this;
        }

        public Criteria andAuthornameIsNotNull() {
            addCriterion("authorName is not null");
            return (Criteria) this;
        }

        public Criteria andAuthornameEqualTo(String value) {
            addCriterion("authorName =", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameNotEqualTo(String value) {
            addCriterion("authorName <>", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameGreaterThan(String value) {
            addCriterion("authorName >", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameGreaterThanOrEqualTo(String value) {
            addCriterion("authorName >=", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameLessThan(String value) {
            addCriterion("authorName <", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameLessThanOrEqualTo(String value) {
            addCriterion("authorName <=", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameLike(String value) {
            addCriterion("authorName like", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameNotLike(String value) {
            addCriterion("authorName not like", value, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameIn(List<String> values) {
            addCriterion("authorName in", values, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameNotIn(List<String> values) {
            addCriterion("authorName not in", values, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameBetween(String value1, String value2) {
            addCriterion("authorName between", value1, value2, "authorname");
            return (Criteria) this;
        }

        public Criteria andAuthornameNotBetween(String value1, String value2) {
            addCriterion("authorName not between", value1, value2, "authorname");
            return (Criteria) this;
        }

        public Criteria andAppdowncountIsNull() {
            addCriterion("appDownCount is null");
            return (Criteria) this;
        }

        public Criteria andAppdowncountIsNotNull() {
            addCriterion("appDownCount is not null");
            return (Criteria) this;
        }

        public Criteria andAppdowncountEqualTo(String value) {
            addCriterion("appDownCount =", value, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountNotEqualTo(String value) {
            addCriterion("appDownCount <>", value, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountGreaterThan(String value) {
            addCriterion("appDownCount >", value, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountGreaterThanOrEqualTo(String value) {
            addCriterion("appDownCount >=", value, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountLessThan(String value) {
            addCriterion("appDownCount <", value, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountLessThanOrEqualTo(String value) {
            addCriterion("appDownCount <=", value, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountLike(String value) {
            addCriterion("appDownCount like", value, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountNotLike(String value) {
            addCriterion("appDownCount not like", value, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountIn(List<String> values) {
            addCriterion("appDownCount in", values, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountNotIn(List<String> values) {
            addCriterion("appDownCount not in", values, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountBetween(String value1, String value2) {
            addCriterion("appDownCount between", value1, value2, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andAppdowncountNotBetween(String value1, String value2) {
            addCriterion("appDownCount not between", value1, value2, "appdowncount");
            return (Criteria) this;
        }

        public Criteria andVersionnameIsNull() {
            addCriterion("versionName is null");
            return (Criteria) this;
        }

        public Criteria andVersionnameIsNotNull() {
            addCriterion("versionName is not null");
            return (Criteria) this;
        }

        public Criteria andVersionnameEqualTo(String value) {
            addCriterion("versionName =", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameNotEqualTo(String value) {
            addCriterion("versionName <>", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameGreaterThan(String value) {
            addCriterion("versionName >", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameGreaterThanOrEqualTo(String value) {
            addCriterion("versionName >=", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameLessThan(String value) {
            addCriterion("versionName <", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameLessThanOrEqualTo(String value) {
            addCriterion("versionName <=", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameLike(String value) {
            addCriterion("versionName like", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameNotLike(String value) {
            addCriterion("versionName not like", value, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameIn(List<String> values) {
            addCriterion("versionName in", values, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameNotIn(List<String> values) {
            addCriterion("versionName not in", values, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameBetween(String value1, String value2) {
            addCriterion("versionName between", value1, value2, "versionname");
            return (Criteria) this;
        }

        public Criteria andVersionnameNotBetween(String value1, String value2) {
            addCriterion("versionName not between", value1, value2, "versionname");
            return (Criteria) this;
        }

        public Criteria andApkmd5IsNull() {
            addCriterion("apkMd5 is null");
            return (Criteria) this;
        }

        public Criteria andApkmd5IsNotNull() {
            addCriterion("apkMd5 is not null");
            return (Criteria) this;
        }

        public Criteria andApkmd5EqualTo(String value) {
            addCriterion("apkMd5 =", value, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5NotEqualTo(String value) {
            addCriterion("apkMd5 <>", value, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5GreaterThan(String value) {
            addCriterion("apkMd5 >", value, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5GreaterThanOrEqualTo(String value) {
            addCriterion("apkMd5 >=", value, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5LessThan(String value) {
            addCriterion("apkMd5 <", value, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5LessThanOrEqualTo(String value) {
            addCriterion("apkMd5 <=", value, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5Like(String value) {
            addCriterion("apkMd5 like", value, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5NotLike(String value) {
            addCriterion("apkMd5 not like", value, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5In(List<String> values) {
            addCriterion("apkMd5 in", values, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5NotIn(List<String> values) {
            addCriterion("apkMd5 not in", values, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5Between(String value1, String value2) {
            addCriterion("apkMd5 between", value1, value2, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andApkmd5NotBetween(String value1, String value2) {
            addCriterion("apkMd5 not between", value1, value2, "apkmd5");
            return (Criteria) this;
        }

        public Criteria andCategorynameIsNull() {
            addCriterion("categoryName is null");
            return (Criteria) this;
        }

        public Criteria andCategorynameIsNotNull() {
            addCriterion("categoryName is not null");
            return (Criteria) this;
        }

        public Criteria andCategorynameEqualTo(String value) {
            addCriterion("categoryName =", value, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameNotEqualTo(String value) {
            addCriterion("categoryName <>", value, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameGreaterThan(String value) {
            addCriterion("categoryName >", value, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameGreaterThanOrEqualTo(String value) {
            addCriterion("categoryName >=", value, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameLessThan(String value) {
            addCriterion("categoryName <", value, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameLessThanOrEqualTo(String value) {
            addCriterion("categoryName <=", value, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameLike(String value) {
            addCriterion("categoryName like", value, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameNotLike(String value) {
            addCriterion("categoryName not like", value, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameIn(List<String> values) {
            addCriterion("categoryName in", values, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameNotIn(List<String> values) {
            addCriterion("categoryName not in", values, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameBetween(String value1, String value2) {
            addCriterion("categoryName between", value1, value2, "categoryname");
            return (Criteria) this;
        }

        public Criteria andCategorynameNotBetween(String value1, String value2) {
            addCriterion("categoryName not between", value1, value2, "categoryname");
            return (Criteria) this;
        }

        public Criteria andAppsizeIsNull() {
            addCriterion("appSize is null");
            return (Criteria) this;
        }

        public Criteria andAppsizeIsNotNull() {
            addCriterion("appSize is not null");
            return (Criteria) this;
        }

        public Criteria andAppsizeEqualTo(String value) {
            addCriterion("appSize =", value, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeNotEqualTo(String value) {
            addCriterion("appSize <>", value, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeGreaterThan(String value) {
            addCriterion("appSize >", value, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeGreaterThanOrEqualTo(String value) {
            addCriterion("appSize >=", value, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeLessThan(String value) {
            addCriterion("appSize <", value, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeLessThanOrEqualTo(String value) {
            addCriterion("appSize <=", value, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeLike(String value) {
            addCriterion("appSize like", value, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeNotLike(String value) {
            addCriterion("appSize not like", value, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeIn(List<String> values) {
            addCriterion("appSize in", values, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeNotIn(List<String> values) {
            addCriterion("appSize not in", values, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeBetween(String value1, String value2) {
            addCriterion("appSize between", value1, value2, "appsize");
            return (Criteria) this;
        }

        public Criteria andAppsizeNotBetween(String value1, String value2) {
            addCriterion("appSize not between", value1, value2, "appsize");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedIsNull() {
            addCriterion("apkDownloaded is null");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedIsNotNull() {
            addCriterion("apkDownloaded is not null");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedEqualTo(Boolean value) {
            addCriterion("apkDownloaded =", value, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedNotEqualTo(Boolean value) {
            addCriterion("apkDownloaded <>", value, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedGreaterThan(Boolean value) {
            addCriterion("apkDownloaded >", value, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("apkDownloaded >=", value, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedLessThan(Boolean value) {
            addCriterion("apkDownloaded <", value, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedLessThanOrEqualTo(Boolean value) {
            addCriterion("apkDownloaded <=", value, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedIn(List<Boolean> values) {
            addCriterion("apkDownloaded in", values, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedNotIn(List<Boolean> values) {
            addCriterion("apkDownloaded not in", values, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedBetween(Boolean value1, Boolean value2) {
            addCriterion("apkDownloaded between", value1, value2, "apkdownloaded");
            return (Criteria) this;
        }

        public Criteria andApkdownloadedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("apkDownloaded not between", value1, value2, "apkdownloaded");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}