package com.example.mongodb.dao;

import com.example.mongodb.domain.StudentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaDaoTest {
    @Autowired
    private JpaDao jpaDao;
    @Test
    public void getStudentScore() throws Exception {
        List<StudentDto> list = jpaDao.getStudentScore(1 , 12);
        list.forEach(s->System.out.println(s));
    }

}