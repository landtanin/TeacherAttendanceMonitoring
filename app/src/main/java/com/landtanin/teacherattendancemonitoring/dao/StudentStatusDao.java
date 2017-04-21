package com.landtanin.teacherattendancemonitoring.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by landtanin on 4/19/2017 AD.
 */

public class StudentStatusDao extends RealmObject implements RealmModel {

    @PrimaryKey
    @SerializedName("_id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("AVG")
    @Expose
    private String aVG;
    @SerializedName("AttendanceStatus")
    @Expose
    private String attendanceStatus;

    public int getIdStatus() {
        return id;
    }

    public void setIdStatus(int id) {
        this.id = id;
    }

    public String getNameStatus() {
        return name;
    }

    public void setNameStatus(String name) {
        this.name = name;
    }

    public String getStudentIdStatus() {
        return studentId;
    }

    public void setStudentIdStatus(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseStatus() {
        return course;
    }

    public void setCourseStatus(String course) {
        this.course = course;
    }

    public String getAVGStatus() {
        return aVG;
    }

    public void setAVGStatus(String aVG) {
        this.aVG = aVG;
    }

    public String getAttendanceStatusStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatusStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
