package com.rocky.rocky.Service.Impl;

import com.rocky.rocky.Entity.MyData;
import com.rocky.rocky.Repository.MyDateRepositoty;
import com.rocky.rocky.Service.MyDataService;
import com.rocky.rocky.Service.helper.CsvHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class MyDataServiceImpl implements MyDataService {

    @Autowired
    MyDateRepositoty myDateRepositoty;

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
}
