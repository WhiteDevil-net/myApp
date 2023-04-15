package com.rocky.rocky.Entity;

import javax.persistence.PrePersist;
import java.util.Date;

public class EntityListener {

    @PrePersist
    public void prePersist(MyData myData){
        myData.setLogDateTime(fetchCurrentDataTime());
    }
    private Date  fetchCurrentDataTime(){
        Date date = new Date(System.currentTimeMillis());
        return date;
    }
}
