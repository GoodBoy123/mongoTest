package com.example.mongodb.dao;

import com.example.mongodb.MongodbApplication;
import com.example.mongodb.domain.PersonDto;
import com.example.mongodb.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDaoTest {

    @Autowired
    private MongoDao mongoDao;
    @Test
    public void insertUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserId(12L);
        userDto.setUsername("Bob");
        userDto.setPassword("123");
        mongoDao.insertUser(userDto);

    }

    //批量插入一个list
    @Test
    public void insertUsers() throws Exception{
        List<UserDto> userDtos = new ArrayList<>();
        UserDto userDto = new UserDto();
        userDto.setUserId(45L);
        userDto.setUsername("小李");
        userDto.setPassword("" + (Math.random()*1000 + 20));

        UserDto userDto2 = new UserDto();
        userDto2.setUserId(59L);
        userDto2.setUsername("大王");
        userDto2.setPassword("654321");

        userDtos.add(userDto);
        userDtos.add(userDto2);

        mongoDao.insertUsers(userDtos);
    }

    //通过用户userId查找
    @Test
    public void getUser() throws Exception {
        List<UserDto> list = mongoDao.getUser(1L);
        System.out.println("size:" + list.size());
        for(UserDto userDto : list)
        {
            System.out.println(userDto);
        }
    }

    //复杂条件查找
    @Test
    public void getUserByMultiCondition() throws Exception {
        Map map = new HashMap();
        map.put("userId" , 12);
        map.put("username" , "Jerry");
        List<UserDto> list = mongoDao.getUserByMultiCondition(map);
        System.out.println("size:" + list.size());
        for(UserDto userDto : list)
        {
            System.out.println(userDto);
        }
    }

    //通过dto的类型查找该类型下的所有数据
    @Test
    public void getAllUser() throws Exception{
        List list = mongoDao.getAllUser(UserDto.class);
        System.out.println("size:" + list.size());
        for(int i = 0 ; i < list.size() ; i++)
        {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void getUserByPage() {
        List list = mongoDao.getUserByPage();
        System.out.println("size:" + list.size());
        for(int i = 0 ; i < list.size() ; i++)
        {
            System.out.println(list.get(i));
        }
    }
    @Test
    public void insertPerson() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setUserId(23L);
        personDto.setName("Marry");
        personDto.setAge(18);
        personDto.setSchool("温州大学");
        personDto.setAddress("浙江温州");
        mongoDao.insertPerson(personDto);

    }

    @Test
    public void getPerson() throws Exception {
        List<PersonDto> list = mongoDao.getPerson("Marry");
        System.out.println("size:" + list.size());
        for(PersonDto personDto : list)
        {
            System.out.println(personDto);
        }

        System.out.println(UserDto.class);
    }

    @Test
    public void updatePerson()
    {
        String username = "Marry";
        Map map = new HashMap();
        map.put("person_id" , 114L);
        map.put("school" , "北京大学");
        mongoDao.updatePerson(username , map);
    }
}