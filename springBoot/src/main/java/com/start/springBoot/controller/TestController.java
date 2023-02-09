package com.start.springBoot.controller;

import com.start.springBoot.dto.TestDTO;
import com.start.springBoot.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
//    Logger logger = LoggerFactory.getLogger(TestController.class);
//    @Autowired
    private final TestService testService;
    public TestController(TestService testService){
        this.testService = testService;
    }


//    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @GetMapping
    public List<TestDTO> getTest(){
        return testService.getAllTest();
    }
    @GetMapping(value = "/{id}")
    public List<TestDTO> findById(@PathVariable("id") Integer id){
        return testService.getOneTest(id);
//        return testService.getOneTest(id);
    }
////    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @PostMapping
    public Integer postTest(
            @RequestBody TestDTO dto
    ){
//        TestDTO insertDTO = testService.insertTest(dto);
//        return new ResponseEntity<TestDTO>(insertDTO, HttpStatus.CREATED);
        return testService.insertTest(dto);
    }
    @PostMapping(value = "/init")
    public boolean initTestData(){
        return testService.initData();
    }
//
    @PutMapping(value = "/{id}")
    public ResponseEntity<TestDTO> updateData(
            @PathVariable("id") Integer id,
            @RequestBody TestDTO dto
    ) {
        ResponseEntity<TestDTO> responseEntity;
        TestDTO afterData = testService.updateTest(id,dto);
        if(afterData==null){
            responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            responseEntity = new ResponseEntity<TestDTO>(afterData, HttpStatus.OK);
        }
        return responseEntity;
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Objects> deleteData(@PathVariable("id") Integer id){
       ResponseEntity<Objects> responseEntity;
       if (testService.deleteData(id)){
           responseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
           return responseEntity;
       }
       responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
       return responseEntity;
    }
}
