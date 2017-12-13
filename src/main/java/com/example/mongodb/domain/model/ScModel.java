package com.example.mongodb.domain.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;



@Entity
@Table(name = "sc")
public class ScModel {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String sno;
    private String cno;
    private double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScModel{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", cno='" + cno + '\'' +
                ", score=" + score +
                '}';
    }
}
