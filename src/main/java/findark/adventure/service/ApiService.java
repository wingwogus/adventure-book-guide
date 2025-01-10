package findark.adventure.service;

import findark.adventure.dto.MarketSearchReq;
import findark.adventure.dto.MarketSearchRes;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Value("${loa.apiKey}")
    private String apiKey;

    @Value("${loa.apiUrl}")
    private String apiUrl;

    HttpHeaders headers;
    RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "bearer " + apiKey);
        restTemplate = new RestTemplate();
    }

    /**
     * 거래소검색
     */
    public ResponseEntity<MarketSearchRes> marketSearch(final MarketSearchReq params) {
        HttpEntity<Object> entity = new HttpEntity<>(params, headers);
        return restTemplate.exchange(
                apiUrl + "/markets/items",
                HttpMethod.POST,
                entity,
                MarketSearchRes.class);
    }

}
