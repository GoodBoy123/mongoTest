package com.example.mongodb.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupByTestDaoTest {

    @Autowired
    private GroupByTestDao groupByTestDao;
    @Test
    public void insertData() throws Exception {
        groupByTestDao.insertDataHZ();
        groupByTestDao.insertDataWZ();
        groupByTestDao.insertDataBZ();
        groupByTestDao.insertDataDL();
        groupByTestDao.insertDataZZ();
        groupByTestDao.insertDataWL();
        groupByTestDao.insertDataWC();

    }

    @Test
    public void getData() throws Exception {
        groupByTestDao.getDataByGroup();
    }


    @Test
    public void getDayRain() throws Exception {
        groupByTestDao.getDayRain();
    }


}