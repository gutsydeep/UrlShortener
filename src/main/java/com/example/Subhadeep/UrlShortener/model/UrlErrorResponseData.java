package com.example.Subhadeep.UrlShortener.model;

public class UrlErrorResponseData
{
    private String status;
    private String error;

    public UrlErrorResponseData(String status, String error) {
        this.status = status;
        this.error = error;
    }

    public UrlErrorResponseData() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "UrlErrorResponseData{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
