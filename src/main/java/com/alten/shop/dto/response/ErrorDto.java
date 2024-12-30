package com.alten.shop.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    @JsonProperty(value = "Timestamp")
    private Date timestamp;

    @JsonProperty(value = "HTTP_CODE")
    private HttpStatus httpCode;

    @JsonProperty(value = "Message")
    private String message;
}
