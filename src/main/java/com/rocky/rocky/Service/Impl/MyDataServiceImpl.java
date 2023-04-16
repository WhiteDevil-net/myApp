package com.rocky.rocky.Service.Impl;

import com.rocky.rocky.Beans.GptRequest;
import com.rocky.rocky.Beans.GptRequestMessages;
import com.rocky.rocky.Entity.MyData;
import com.rocky.rocky.Repository.MyDateRepositoty;
import com.rocky.rocky.Service.MyDataService;
import com.rocky.rocky.Service.helper.CsvHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class MyDataServiceImpl implements MyDataService {

    @Autowired
    MyDateRepositoty myDateRepositoty;

    @Value("${gpt.key}")
    String gptApiKey;
    @Value("${gpt.Url}")
    String gptUrl;
    @Value("${image.generate}")
    String imageGenerate;

    @Override
    public MyData saveRecord(MyData data){
        return myDateRepositoty.save(data);
    }
    @Override
    public List<MyData> getAllData(){
        List<MyData> myData = myDateRepositoty.findAll();
        return myData;
    }
    @Override
    public Optional<MyData> getSomeData(Integer id){
        return myDateRepositoty.findById(id);
    }
    @Override
    public void loadData(MultipartFile file) throws Exception{
        List<MyData> myDataList = CsvHelper.convertCsvToMyData(file.getInputStream());
        myDateRepositoty.saveAll(myDataList);
    }
    @Override
    public String  gpt(String message1){
        RestTemplate restTemplate = new RestTemplate();
        GptRequest request = new GptRequest();
        request.setModel("gpt-3.5-turbo");
        GptRequestMessages messages = new GptRequestMessages();
        messages.setRole("user");
        messages.setContent(message1);
        request.setMessages(Collections.singletonList(messages));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(gptApiKey);
        ResponseEntity<Map<String,Object>> responseEntity = restTemplate.exchange(gptUrl, HttpMethod.POST, new HttpEntity<>(request,headers), new ParameterizedTypeReference<Map<String, Object>>() {
        });
        Map<String,Object> result = responseEntity.getBody();
        List<Map<String,Object>> choices = (List<Map<String, Object>>) result.get("choices");
        Map<String,Object> message = (Map<String, Object>) choices.get(0).get("message");
        return (String) message.get("content");
}
@Override
   public Map<String, Object> imageGeneration(String message){

       RestTemplate restTemplate = new RestTemplate();
       Map<String,Object> map = new HashMap<>();
       map.put("prompt",message);
       map.put("n",1);
       map.put("size", "1024x1024");
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setBearerAuth(gptApiKey);
       ResponseEntity<Map<String,Object>> response = restTemplate.exchange(imageGenerate, HttpMethod.POST, new HttpEntity<>(map, headers), new ParameterizedTypeReference<Map<String, Object>>() {
       });
       return response.getBody();

   }
}
