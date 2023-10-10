package com.example.Subhadeep.UrlShortener.Controller;

import com.example.Subhadeep.UrlShortener.model.Url;
import com.example.Subhadeep.UrlShortener.model.UrlData;
import com.example.Subhadeep.UrlShortener.model.UrlErrorResponseData;
import com.example.Subhadeep.UrlShortener.model.UrlResponseData;
import com.example.Subhadeep.UrlShortener.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class UrlShorteningController
{
    @Autowired
    private UrlService urlService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlData urlData)
    {
        Url urlToRet = urlService.generateShortLink(urlData);

        if(urlToRet != null)
        {
            UrlResponseData urlResponseData = new UrlResponseData();
            urlResponseData.setOriginalUrl(urlToRet.getOriginalUrl());
            urlResponseData.setExpirationDate(urlToRet.getExpirationDate());
            urlResponseData.setShortLink(urlToRet.getShortLink());
            return new ResponseEntity<UrlResponseData>(urlResponseData, HttpStatus.OK);
        }

        UrlErrorResponseData urlErrorResponseData = new UrlErrorResponseData();
        urlErrorResponseData.setStatus("404");
        urlErrorResponseData.setError("There was an error processing your request. please try again.");
        return new ResponseEntity<UrlErrorResponseData>(urlErrorResponseData,HttpStatus.OK);

    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {

        if(StringUtils.isEmpty(shortLink))
        {
            UrlErrorResponseData urlErrorResponseData = new UrlErrorResponseData();
            urlErrorResponseData.setError("Invalid Url");
            urlErrorResponseData.setStatus("400");
            return new ResponseEntity<UrlErrorResponseData>(urlErrorResponseData,HttpStatus.OK);
        }
        Url urlToRet = urlService.getEncodedUrl(shortLink);

        if(urlToRet == null)
        {
            UrlErrorResponseData urlErrorResponseData = new UrlErrorResponseData();
            urlErrorResponseData.setError("Url does not exist or it might have expired!");
            urlErrorResponseData.setStatus("400");
            return new ResponseEntity<UrlErrorResponseData>(urlErrorResponseData,HttpStatus.OK);
        }

        if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now()))
        {
            urlService.deleteShortLink(urlToRet);
            UrlErrorResponseData urlErrorResponseData = new UrlErrorResponseData();
            urlErrorResponseData.setError("Url Expired. Please try generating a fresh one.");
            urlErrorResponseData.setStatus("200");
            return new ResponseEntity<UrlErrorResponseData>(urlErrorResponseData,HttpStatus.OK);
        }

        response.sendRedirect(urlToRet.getOriginalUrl());
        return null;
    }
}

