package com.start.springBoot.repository;

import com.start.springBoot.dto.TestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository1 {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    public List<TestDTO> findAll(){
        return sqlSessionTemplate.selectList("test.findAll");
    }
    public List<TestDTO> findOneById(Integer id){
        return sqlSessionTemplate.selectList("test.findOneById", id);
    }
    public Integer insertOne(TestDTO dto) {
        return sqlSessionTemplate.insert("test.insertOne", dto);
    }
}