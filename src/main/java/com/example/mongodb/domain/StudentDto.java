package com.example.mongodb.domain;

public class StudentDto {

    private String sno;
    private String sname;
    private String cname;
    private Double score;

    public StudentDto(String sno, String sname, String cname, Double score) {
        this.sno = sno;
        this.sname = sname;
        this.cname = cname;
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", cname='" + cname + '\'' +
                ", score=" + score +
                '}';
    }
}
