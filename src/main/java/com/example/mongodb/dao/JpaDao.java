package com.example.mongodb.dao;

import com.example.mongodb.domain.StudentDto;
import com.example.mongodb.domain.model.CourseMoel;
import com.example.mongodb.domain.model.ScModel;
import com.example.mongodb.domain.model.StudentModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class JpaDao {
    @Autowired
    private EntityManager entityManager;
    public List<StudentDto> getStudentScore(int pageIndex, int pageSize)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //结果保存对象
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(StudentDto.class);
        //要进行查找的表所映射的实体类
        Root student = criteriaQuery.from(StudentModel.class);
        Root course = criteriaQuery.from(CourseMoel.class);
        Root sc = criteriaQuery.from(ScModel.class);

        //查找条件
        List<Predicate> predicateList = Lists.newArrayList();
        predicateList.add(criteriaBuilder.equal(student.get("sno").as(String.class), sc.get("sno")));
        predicateList.add(criteriaBuilder.equal(course.get("cno").as(String.class), sc.get("cno")));

        criteriaQuery = criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        //选取的属性对应，上面结果保存对象中相应的构造器
        criteriaQuery = criteriaQuery.multiselect(student.get("sno"), student.get("sname"), course.get("cname"),
                sc.get("score"));

        Query query = entityManager.createQuery(criteriaQuery);
        //对查询结果进行分页
        query.setMaxResults(pageSize);
        query.setFirstResult((pageIndex - 1) * pageSize);

        return query.getResultList();
    }
}
