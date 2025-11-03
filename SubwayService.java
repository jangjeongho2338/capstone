package com.project.subway.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubwayService {

    private final WebClient seoulWebClient;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${seoul.subway.api-key}")
    private String apiKey;

    public Mono<JsonNode> realtime(String line, int start, int end) {
        return seoulWebClient.get()
                .uri(b -> b
                        // baseUrl = http://swopenapi.seoul.go.kr/api/subway
                        .pathSegment(apiKey, "json", "realtimePosition",
                                Integer.toString(start), Integer.toString(end), line) // ★ 수동 인코딩 금지
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .map(json -> {
                    try { return mapper.readTree(json); }
                    catch (Exception e) { throw new RuntimeException(e); }
                });
    }
}
