package findark.adventure.controller;

import findark.adventure.dto.MarketSearchReq;
import findark.adventure.dto.MarketSearchRes;
import findark.adventure.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService _apiService;

    /**
     * 거래소 검색
     */
    @PostMapping("/market/search")
    public ResponseEntity<MarketSearchRes> marketSearch(@RequestBody MarketSearchReq params) {
        return _apiService.marketSearch(params);
    }
}
