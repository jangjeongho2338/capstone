// src/main/java/com/project/subway/dto/RealtimePositionResponse.java
package com.project.subway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealtimePositionResponse {
    private ErrorMessage errorMessage;
    private List<Row> realtimePositionList;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ErrorMessage {
        private int status;
        private String code;
        private String message;
        private String link;
        private String developerMessage;
        private int total;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Row {
        private Integer rowNum;
        private String subwayId;
        private String subwayNm;
        private String statnId;
        private String statnNm;
        private String trainNo;
        private String updnLine;
        private String lastRecptnDt;
        private String recptnDt;
        private String statnTid;
        private String statnTnm;
        private String trainSttus;
        private String directAt;
        private String lstcarAt;
    }
}
