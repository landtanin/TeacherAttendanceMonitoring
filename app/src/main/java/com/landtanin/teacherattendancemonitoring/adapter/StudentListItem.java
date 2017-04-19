package com.landtanin.teacherattendancemonitoring.adapter;

/**
 * Created by landtanin on 2/15/2017 AD.
 */

public class StudentListItem {

    public String studentNameTxt, studentIdTxt;

    public StudentListItem(String studentNameTxt, String studentIdTxt) {
        this.studentNameTxt = studentNameTxt;
        this.studentIdTxt = studentIdTxt;
    }

    public String getStudentNameTxt() {
        return studentNameTxt;
    }

    public void setStudentNameTxt(String studentNameTxt) {
        this.studentNameTxt = studentNameTxt;
    }

    public String getStudentIdTxt() {
        return studentIdTxt;
    }

    public void setStudentIdTxt(String studentIdTxt) {
        this.studentIdTxt = studentIdTxt;
    }
}
