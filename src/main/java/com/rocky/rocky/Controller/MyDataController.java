package com.rocky.rocky.Controller;

import com.rocky.rocky.Entity.MyData;
import com.rocky.rocky.Entity.RestResponce;
import com.rocky.rocky.Service.MyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class MyDataController {

    @Autowired
    MyDataService myDataService;


    @GetMapping(value = "/getAllRecords")
    public ResponseEntity<RestResponce> getAllRecords(){
        try {
            List<MyData> myData = myDataService.getAllData();
            return new ResponseEntity<>(new RestResponce(HttpStatus.OK,"success",myData),HttpStatus.OK);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<RestResponce> saveRecord(@RequestBody MyData data)
    {
        MyData myData = myDataService.saveRecord(data);
        return new ResponseEntity<>(new RestResponce(HttpStatus.OK,"success",myData),HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<RestResponce> getById(@PathVariable Integer id){
        try {
            Optional<MyData> myData = myDataService.getSomeData(id);
            return new ResponseEntity<>(new RestResponce(HttpStatus.OK,"success",myData),HttpStatus.OK);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @PostMapping(value = "/loadData")
    public ResponseEntity<RestResponce> loadData(@RequestBody MultipartFile file) throws Exception{
        try {
            myDataService.loadData(file);
            return new ResponseEntity<>(new RestResponce(HttpStatus.OK,"success","Ok"),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new RestResponce(HttpStatus.OK,"error Occurred",""),HttpStatus.OK);
        }
    }
}
