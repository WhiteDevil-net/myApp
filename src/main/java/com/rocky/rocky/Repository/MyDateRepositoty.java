package com.rocky.rocky.Repository;

import com.rocky.rocky.Entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDateRepositoty extends JpaRepository<MyData,Integer> {
}
