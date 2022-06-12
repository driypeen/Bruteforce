package com.driypeen.Bruteforce.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonRootName("Error")
public class ErrorResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("dataTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime dataTime;

    public ErrorResponse(String message) {
        this.message = message;
        this.dataTime = LocalDateTime.now();
    }
}