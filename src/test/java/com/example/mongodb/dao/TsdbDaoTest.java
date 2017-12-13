package com.example.mongodb.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TsdbDaoTest {
    @Autowired
    private TsdbDao tsdbDao;

    @Test
    public void insertData() throws Exception {
        tsdbDao.insertData();
    }

    @Test
    public void insertData2() throws Exception {
        String[] array = new String[]{"200W" , "210W" , "220W"};
        tsdbDao.insertData2(array);
    }

    @Test
    public void getMetrics(){
        tsdbDao.getMetrics();
    }

    @Test
    public void getTags(){
        tsdbDao.getTags();
    }

    @Test
    public void getFields(){
        tsdbDao.getFields();
    }


    @Test
    public void insertDataList() throws Exception {
        tsdbDao.insertDataList();
    }

    //单域查找
    @Test
    public void getData() throws Exception {
        String METRIC = "windTest";
        String FIELD = "value";
        tsdbDao.getData(METRIC , FIELD);
    }

    //多域查找
    @Test
    public void queryMetrics() throws Exception{
        tsdbDao.queryMetrics();
    }

}