package com.landtanin.teacherattendancemonitoring.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by landtanin on 4/19/2017 AD.
 */

public class User extends RealmObject implements RealmModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lecturer_id")
    @Expose
    private int lecturerId;
    @SerializedName("email")
    @Expose
    private String email;

    @PrimaryKey
    @SerializedName("unique_id")
    @Expose
    private String uniqueId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

}
