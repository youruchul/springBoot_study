package com.start.springBoot.service;

import com.start.springBoot.dto.TestDTO;
import com.start.springBoot.repository.TestRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    TestRepository1 repository1;
    List<TestDTO> db = new ArrayList<>();

    public List<TestDTO> getAllTest(){
        return repository1.findAll();
    }

    public List<TestDTO> getOneTest(Integer id){
//        Optional<TestDTO> findOneById = db.stream().filter(dto -> Objects.equals(dto.getId(), id)).findFirst();
//        return findOneById.orElse(null);
        return repository1.findOneById(id);
    }
    public Integer insertTest(TestDTO dto){
//        int id = db.size() + 1;
//        dto.setId(id);
//        db.add(dto);
        return repository1.insertOne(dto);
    }

    public boolean initData() {
        int size = db.size();
        for (int i=size + 1; i<=size + 5; i++){
            TestDTO dto = new TestDTO(i, "park", 10*i);
            db.add(dto);
        }
        return true;
    }

    public TestDTO updateTest(Integer id, TestDTO dto){
        dto.setId(id);
        try{
            db.remove(id-1);
            db.add(id-1, dto);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return dto;
    }

    public Boolean deleteData (Integer id){
        try{
            db.remove(id-1);
        } catch (IndexOutOfBoundsException e) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return false;
        }
        return true;
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
