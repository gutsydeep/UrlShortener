package com.example.Subhadeep.UrlShortener.service;

import com.example.Subhadeep.UrlShortener.model.Url;
import com.example.Subhadeep.UrlShortener.model.UrlData;
import org.springframework.stereotype.Service;

@Service
public interface UrlService
{
    public Url generateShortLink(UrlData urlData);
    public Url persistShortLink(Url url);
    public Url getEncodedUrl(String url);
    public  void  deleteShortLink(Url url);
}
