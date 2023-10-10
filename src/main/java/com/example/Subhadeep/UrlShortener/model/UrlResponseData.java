package com.example.Subhadeep.UrlShortener.model;

import java.time.LocalDateTime;

public class UrlResponseData
{
    private String originalUrl;
    private String shortLink;
    private LocalDateTime expirationDate;

    public UrlResponseData(String originalUrl, String shortLink, LocalDateTime expirationDate) {
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.expirationDate = expirationDate;
    }

    public UrlResponseData() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlResponseData{" +
                "originalUrl='" + originalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}

