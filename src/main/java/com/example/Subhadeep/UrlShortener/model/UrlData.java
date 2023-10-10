package com.example.Subhadeep.UrlShortener.model;

public class UrlData
{
    private String url;
    private String expirationDate;
    private String userType;
    private String slashTag;

    public UrlData(String url, String expirationDate, String userType , String slashTag) {
        this.url = url;
        this.expirationDate = expirationDate;
        this.userType = userType;
        this.slashTag = slashTag;
    }

    public UrlData() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSlashTag() {
        return slashTag;
    }

    public void setSlashTag(String slashTag) {
        this.slashTag = slashTag;
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "url='" + url + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", userType='" + userType + '\'' +
                ", slashTag='" + slashTag + '\'' +
                '}';
    }
}

