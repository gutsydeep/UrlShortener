package com.example.Subhadeep.UrlShortener.service;

import com.google.common.hash.Hashing;
import com.example.Subhadeep.UrlShortener.model.Url;
import com.example.Subhadeep.UrlShortener.model.UrlData;
import com.example.Subhadeep.UrlShortener.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService {

    private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlData urlData) {

        if(StringUtils.equals("pro",urlData.getUserType())){
            Url urlToPersist = new Url();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setOriginalUrl(urlData.getUrl());
            urlToPersist.setExpirationDate(getExpirationDate(urlData,urlData.getExpirationDate(),urlToPersist.getCreationDate()));
            urlToPersist.setShortLink(urlData.getSlashTag());
            Url urlToRet = persistShortLink(urlToPersist);
            return urlToRet;
        }


        else {
            if (StringUtils.isNotEmpty(urlData.getUrl())) {
                String encodedUrl = encodeUrl(urlData.getUrl());
                Url urlToPersist = new Url();
                urlToPersist.setCreationDate(LocalDateTime.now());
                urlToPersist.setOriginalUrl(urlData.getUrl());
                urlToPersist.setShortLink(encodedUrl);
                urlToPersist.setExpirationDate(getExpirationDate(urlData,urlData.getExpirationDate(), urlToPersist.getCreationDate()));
                Url urlToRet = persistShortLink(urlToPersist);

                if (urlToRet != null)
                    return urlToRet;

                return null;
            }
        }
        return null;
    }

    private LocalDateTime getExpirationDate(UrlData urlData, String expirationDate, LocalDateTime creationDate)
    {
        if(StringUtils.equals("normal",urlData.getUserType()) || (StringUtils.isBlank(expirationDate)))
        {
            return creationDate.plusSeconds(60);
        }
        LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
        return expirationDateToRet;
    }

    private String encodeUrl(String url)
    {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return  encodedUrl;
    }

    @Override
    public Url persistShortLink(Url url) {
        Url urlToRet = urlRepository.save(url);
        return urlToRet;
    }

    @Override
    public Url getEncodedUrl(String url) {
        Url urlToRet = urlRepository.findByShortLink(url);
        return urlToRet;
    }

    @Override
    public void deleteShortLink(Url url){
        urlRepository.delete(url);
    }
}