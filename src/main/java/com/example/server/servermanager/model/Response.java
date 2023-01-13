package com.example.server.servermanager.model;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private LocalDateTime date;
    private int statusCode;
    private String status;
    private String message;
    private String reason;
    private Map<?, ?> data;

}
