package com.project.subway.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.subway.service.SubwayService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import org.springframework.web.util.UriUtils;

@RestController
@RequestMapping("/api/subway")
@Validated
@RequiredArgsConstructor
public class SubwayController {

    private final SubwayService subwayService;

    @GetMapping(value="/realtime/{line}/{start}/{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<JsonNode>> realtimePath(
            @PathVariable String line,
            @PathVariable @Min(0) int start,
            @PathVariable @Max(1000) int end) {

        String cleaned = line == null ? "" : line.trim();
        cleaned = java.text.Normalizer.normalize(cleaned, java.text.Normalizer.Form.NFC);
        if (cleaned.contains("%")) {
            cleaned = org.springframework.web.util.UriUtils.decode(cleaned, java.nio.charset.StandardCharsets.UTF_8);
        }

        return subwayService.realtime(cleaned, start, end).map(ResponseEntity::ok);
    }

    @GetMapping(value="/realtime", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<JsonNode>> realtimeQuery(
            @RequestParam String line,
            @RequestParam @Min(0) int start,
            @RequestParam @Max(1000) int end) {

        String cleaned = line == null ? "" : line.trim();
        cleaned = java.text.Normalizer.normalize(cleaned, java.text.Normalizer.Form.NFC);
        if (cleaned.contains("%")) {
            cleaned = org.springframework.web.util.UriUtils.decode(cleaned, java.nio.charset.StandardCharsets.UTF_8);
        }

        return subwayService.realtime(cleaned, start, end).map(ResponseEntity::ok);
    }
}
