package com.landtanin.teacherattendancemonitoring.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by landtanin on 4/19/2017 AD.
 */

public class StudentAttendanceDao extends RealmObject implements RealmModel {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAVG() {
        return aVG;
    }

    public void setAVG(String aVG) {
        this.aVG = aVG;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
