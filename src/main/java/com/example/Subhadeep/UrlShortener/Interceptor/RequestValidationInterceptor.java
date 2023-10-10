package com.example.Subhadeep.UrlShortener.Interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isValid = validateRequest(request);
        System.out.println("Request is valid  " + isValid);
        return isValid;
    }

    private boolean validateRequest(HttpServletRequest request) {
        String destinationUrl = request.getParameter("destination_url");
        String slashTag = request.getParameter("slash_tag");
        String userTypeJson = request.getParameter("user_type");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(userTypeJson);
            if (jsonNode.has("user_type") && "pro".equals(jsonNode.get("user_type").asText())) {
                if (destinationUrl != null && !destinationUrl.isEmpty() && slashTag != null && !slashTag.isEmpty()) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }
}
