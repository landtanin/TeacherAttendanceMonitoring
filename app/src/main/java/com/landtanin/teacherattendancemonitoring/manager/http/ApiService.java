package com.landtanin.teacherattendancemonitoring.manager.http;

import com.landtanin.teacherattendancemonitoring.dao.LecturerModuleCollectionDao;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by landtanin on 4/19/2017 AD.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("lecturerLogin/index.php")
    Observable<LecturerModuleCollectionDao> lecturerLoginCheck
            (@Field("tag") String tag,
             @Field("email") String email,
             @Field("password") String password);

    @GET("lecturerModuleGet.php")
    Observable<LecturerModuleCollectionDao> loadLecturerModule
            (@Query("lecturer_id") int lecturer_id);

    @GET("studentAttendanceGet.php")
    Observable<LecturerModuleCollectionDao> loadModuleStudent
            (@Query("module_id") String module_id);

    @GET("studentStatusGet.php")
    Observable<LecturerModuleCollectionDao> loadStudentStatus
            (@Query("module_id") String module_id);

    @FormUrlEncoded
    @POST("lecturerStatusClear.php")
    Call<ResponseBody> attendanceUpdate
            (@Field("status") String status,
             @Field("module_id") String module_id);

}
