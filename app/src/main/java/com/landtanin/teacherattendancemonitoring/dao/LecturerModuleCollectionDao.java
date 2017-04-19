package com.landtanin.teacherattendancemonitoring.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by landtanin on 4/19/2017 AD.
 */

public class LecturerModuleCollectionDao {

    @SerializedName("modules")
    @Expose
    private List<LecturerModuleDao> data = null;

    public List<LecturerModuleDao> getData() {
        return data;
    }

    // ---------------------------------------------------------------

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private User user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // ---------------------------------------------------------------

    @SerializedName("students")
    @Expose
    private List<StudentAttendanceDao> students = null;

    public List<StudentAttendanceDao> getStudents() {
        return students;
    }

    public void setStudents(List<StudentAttendanceDao> students) {
        this.students = students;
    }

    // ---------------------------------------------------------------

    @SerializedName("students_status")
    @Expose
    private List<StudentStatusDao> students_status = null;

    public List<StudentStatusDao> getStudentsStatus() {
        return students_status;
    }

    public void setStudentsStatus(List<StudentStatusDao> students_status) {
        this.students_status = students_status;
    }

}
