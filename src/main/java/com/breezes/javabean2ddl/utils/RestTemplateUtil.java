package com.breezes.javabean2ddl.utils;

import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author breezes_y@163.com
 * @date 2021/2/5 11:25
 * @description
 */
public class RestTemplateUtil {

    private final RestTemplate restTemplate;

    public RestTemplateUtil() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(2000);
        clientHttpRequestFactory.setReadTimeout(2000);

        // 因为restTemplate默认只接受200状态码，这里设置非200状态码 也不会抛出异常
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) {
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse response) {
            }
        };

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.setErrorHandler(responseErrorHandler);
        this.restTemplate = restTemplate;
    }

    public String post(String url, Map<String, String> headerParam, Object params, MediaType mediaType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);
            for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                headers.add(entry.getKey(), entry.getKey());
            }
            HttpEntity<Object> request = new HttpEntity<Object>(params, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
