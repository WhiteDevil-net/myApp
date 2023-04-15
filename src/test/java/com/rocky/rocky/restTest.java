package com.rocky.rocky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class restTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ServiceImpl service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void restTest(){
        Mockito.when(restTemplate.exchange(any(),any(),any(),eq(new ParameterizedTypeReference<>(){}))).thenReturn(new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK));
        service.test();
    }
}
