package com.example.mongodb.dao;


import com.example.mongodb.domain.PersonDto;
import com.example.mongodb.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Repository
public class MongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertUser(UserDto userDto)
    {
        mongoTemplate.save(userDto);
    }

    public void insertUsers(List<UserDto> list)
    {
        mongoTemplate.insert(list , UserDto.class);
    }

    //查询键userId应为UserDto中的属性，而不是collection中的域
    public List<UserDto> getUser(Long userId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<UserDto> list = mongoTemplate.find(query , UserDto.class);
        return list;
    }

    //传入查询条件map
    public List<UserDto> getUserByMultiCondition(Map map)
    {
        Query query = new Query();
        //创建查询的list
        List<Criteria> list = new ArrayList<>();
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String , String> entry = (Map.Entry<String, String>)iterator.next();
            Criteria criteria = Criteria.where(entry.getKey()).is(entry.getValue());
            list.add(criteria);
        }
        Criteria[] criterias;

        //处理查询条件为空的情形
        if(list != null && list.size() > 0)
        {
            criterias = new Criteria[list.size()];
            list.toArray(criterias);
        }else{
            criterias = new  Criteria[]{Criteria.where("")};
        }
        //andOperator表示criterias数组中的查询条件均要满足 ； orOperator表示或者的意思
        query.addCriteria(Criteria.where("").andOperator(criterias));
        List<UserDto> listUserDto = mongoTemplate.find(query , UserDto.class);
        return listUserDto;
    }

    public List getAllUser(Class userDtoClass)
    {
        String[] str = userDtoClass.toString().split(" ");
        Query query = new Query();
        query.addCriteria(Criteria.where("_class").is(str[1]));
        List list = mongoTemplate.find(query , userDtoClass);
        return list;
    }

    public List<UserDto> getUserByPage()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(""));
        query.with(new Sort(Sort.Direction.ASC , "password"));
        query.with(new Sort(Sort.Direction.DESC,"userId"));
        query.skip(1).limit(10);
        List list = mongoTemplate.find(query , UserDto.class);
        return list;
    }


    public void insertPerson(PersonDto personDto)
    {

        mongoTemplate.save(personDto);
    }

    public List<PersonDto> getPerson(String username)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        List<PersonDto> list = mongoTemplate.find(query , PersonDto.class);
        return list;
    }

    /**
     *
     * @param username
     *        根据username查找用户
     * @param params
     *        需要修改的参数和期望修改的值以键值对的方式存在map中
     */
    public void updatePerson(String username , Map params)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        Update update = new Update();

        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> entry= (Map.Entry<String, String>) iterator.next();
            update.set(entry.getKey() , entry.getValue());
        }
        mongoTemplate.updateFirst(query , update ,PersonDto.class);

    }
}
