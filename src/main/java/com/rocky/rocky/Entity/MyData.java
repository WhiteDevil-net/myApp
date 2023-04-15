package com.rocky.rocky.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class MyData {

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private String phnNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logDateTime;
}
