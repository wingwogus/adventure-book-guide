package findark.adventure.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import findark.adventure.dto.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

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

    //캐릭터 검색
    public ResponseEntity<CharacterRes> characterSearch(CharacterReq params) {
        ResponseEntity<String> jsonResult = restTemplate.exchange(
                apiUrl + "/characters/" + params.getCharacterName() + "/siblings",
                HttpMethod.GET,
                _getEntity(null),
                String.class);

        List<CharacterInfo> characterInfo = _getArrayData(jsonResult, CharacterInfo.class);

        if(characterInfo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for(CharacterInfo info : characterInfo){
            String name = info.getCharacterName();
            CharacterInfo.ArmoryProfile armoryProfile = restTemplate.exchange(
                    apiUrl + "/armories/characters/" + name + "/profiles",
                    HttpMethod.GET,
                    _getEntity(null),
                    CharacterInfo.ArmoryProfile.class).getBody();
            info.setArmoryProfile(armoryProfile);
        }

        CharacterRes res = new CharacterRes();
        res.setCharacterInfo(characterInfo);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private HttpEntity _getEntity(Object obj) {
        return obj == null ? new HttpEntity<>(headers) : new HttpEntity<>(obj, headers);
    }

    private <T> List<T> _getArrayData(ResponseEntity<String> str, Class<T> clazz) {
        String jsonArray = str.getBody();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
