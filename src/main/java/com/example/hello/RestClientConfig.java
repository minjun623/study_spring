package com.example.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration // 컨테이너 셋업때 봐줘 하는 라벨
public class RestClientConfig {

    @Bean // 이 메서드 반환값을 spring 컨테이너에 등록
    public RestClient restClient() {
        return RestClient.builder().build();
        // 가장 기본꼴의 http/https 클라이언트
        // 나중에 .get().uri(...).retrieve().body(...) 로 사용
    }
}