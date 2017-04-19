package com.landtanin.teacherattendancemonitoring.manager.http;

import com.landtanin.teacherattendancemonitoring.dao.LecturerModuleCollectionDao;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by landtanin on 4/19/2017 AD.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("lecturerLogin/index.php")
    rx.Observable<LecturerModuleCollectionDao> lecturerLoginCheck
            (@Field("tag") String tag,
             @Field("email") String email,
             @Field("password") String password);

    @FormUrlEncoded
    @POST("teacherModuleGet.php")
    rx.Observable<LecturerModuleCollectionDao> loadLecturerModule(@Field("lecturer_id") int lecturer_id);


    @FormUrlEncoded
    @POST("studentAttendanceGet.php")
    rx.Observable<LecturerModuleCollectionDao> loadModuleStudent(@Field("module_id") String module_id);

}
