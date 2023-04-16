package com.rocky.rocky.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestResponce {
    private HttpStatus status;
    private String message;
    private  Object body;

    public <T> RestResponce(HttpStatus status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }
}
