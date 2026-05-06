package com.example.hello.holiday;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import java.net.URI;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class HolidayController {

    private final RestClient restClient;

    @Value("${public-api.service-key}")
    private String apiKey;

    @Value("${public-api.holiday-url}")
    private String holidayUrl;
    // 생성자 주입 (외부 API 호출 필요 -> 만들어둔 가장 기본꼴의 http 클라이언트 필요 하다 선언만 -> 이후에 spring이 알아서 넣어줌)
    public HolidayController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/holiday")
    public String checkHoliday(@RequestParam String date) {
        // date 형식: "20260601"
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("apis.data.go.kr")
                        .path("/B090041/openapi/service/SpcdeInfoService/getRestDeInfo")
                        .queryParam("serviceKey", apiKey)
                        .queryParam("solYear", year)
                        .queryParam("solMonth", month)
                        .queryParam("_type", "json")
                        .build())
                .retrieve()
                .body(String.class);
    }
    @GetMapping("/holiday_uri")
    public String checkHoliday_with_URI(@RequestParam String date) {
        // date 형식: "20260601"
        /*
            URI란? 자원 식별 문자열 (= 큰 개념)
            ├── URL : 자원의 위치를 알려주는 URI
            │         ex) https://google.com/search
            └── URN : 자원의 이름만 알려주는 URI (위치 모름)
                      ex) urn:isbn:9780321125217 (책 ISBN)
             URI class : 문자열 파싱만 (네트워크 호출 X)
             URL class : 자동 dns 조회
        * */
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        URI uri = UriComponentsBuilder.fromUriString(holidayUrl)
                .queryParam("serviceKey", apiKey)
                .queryParam("solYear", year)
                .queryParam("solMonth", month)
                .queryParam("_type", "json")
                .build()
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve() // api 호출 및 응답
                .body(String.class); // return type 지정
    }
}