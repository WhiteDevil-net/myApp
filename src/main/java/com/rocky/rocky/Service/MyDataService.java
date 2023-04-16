package com.rocky.rocky.Service;

import com.rocky.rocky.Entity.MyData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MyDataService {
    MyData saveRecord(MyData myData);
    List<MyData> getAllData();
    Optional<MyData> getSomeData(Integer id);

    void loadData(MultipartFile file) throws Exception;

    String  gpt(String message);


    Map<String, Object> imageGeneration(String message);
}
