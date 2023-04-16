package com.rocky.rocky.Beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GptRequest {
    String model;
    List<GptRequestMessages> messages;
}
