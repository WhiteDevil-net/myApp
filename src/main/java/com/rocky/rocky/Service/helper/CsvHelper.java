package com.rocky.rocky.Service.helper;

import com.rocky.rocky.Entity.MyData;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CsvHelper {
    public static List<MyData> convertCsvToMyData(InputStream stream) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(stream));
        List<MyData> myDataList = new ArrayList<>();
        String line = fileReader.readLine();
        int i=0;
        while (line != null) {
            if (i != 0) {
                String[] records = line.split(",");
                MyData myData = new MyData(Integer.parseInt(records[0]), records[1], Integer.parseInt(records[2]), records[3], records[4], records[5], new Date());
                myDataList.add(myData);
            }
            line = fileReader.readLine() != null? fileReader.readLine() +1 : null;
            i++;
        }
        return myDataList;
    }
}
