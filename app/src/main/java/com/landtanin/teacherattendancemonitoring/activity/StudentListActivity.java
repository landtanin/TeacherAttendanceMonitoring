package com.landtanin.teacherattendancemonitoring.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.landtanin.teacherattendancemonitoring.R;
import com.landtanin.teacherattendancemonitoring.adapter.StudentListAdapter;
import com.landtanin.teacherattendancemonitoring.databinding.ActivityStudentListBinding;
import com.landtanin.teacherattendancemonitoring.fragment.FragmentStudent;

public class StudentListActivity extends AppCompatActivity {

    ActivityStudentListBinding b;
    private String moduleId;
    private StudentListAdapter mStudentListAdapter;
//    private List<StudentListItem> mStudentListItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_student_list);

        initInstance();

        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.studentListContentContainer,
                            FragmentStudent.newInstance(),
                            "FragmentStudent")
                    .commit();
        }

    }

    private void initInstance() {

        moduleId = getIntent().getStringExtra("module_id");
        setSupportActionBar(b.studentListActivityToolbar);
        b.txtToolbarMainActivity.setText(moduleId);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (item.getItemId() == R.id.action_refresh) {

//            dialog = new ProgressDialog(this);
//            dialog.setMessage("Refreshing...");
//            dialog.setCancelable(false);
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.show();
//
//            ApiService apiService = HttpManager.getInstance().create(ApiService.class);
////        apiService.loadStudentModule(Authorization,Content_Type,developer.getMemberID(),TopicId)
//            apiService.loadStudentStatus(moduleId)
//                    .asObservable()
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Utils.getInstance().defaultSubscribeScheduler())
//                    .unsubscribeOn(Utils.getInstance().defaultSubscribeScheduler())
//                    .subscribe(new Action1<LecturerModuleCollectionDao>() {
//                        @Override
//                        public void call(LecturerModuleCollectionDao response) {
//
//                            Realm realm = Realm.getDefaultInstance();
//                            realm.beginTransaction();
////                        realm.deleteAll(); // clear the current data before load new data
//                            realm.delete(StudentStatusDao.class); // delete only data of a specific class
//                            realm.copyToRealmOrUpdate(response.getData());
//                            realm.commitTransaction();
//                            dialog.dismiss();
//
//                            Log.d("MainActivity getStudent", "call success");
//
//                        }
//
//                    }, new Action1<Throwable>() {
//                        @Override
//                        public void call(Throwable throwable) {
//                            dialog.dismiss();
//                            Utils.getInstance().onHoneyToast("MainActivity STUDENT "+throwable.getLocalizedMessage());
//
//                        }
//                    });

//        }

//        return super.onOptionsItemSelected(item);
//    }



}
