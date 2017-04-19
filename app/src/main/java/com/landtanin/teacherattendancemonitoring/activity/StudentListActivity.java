package com.landtanin.teacherattendancemonitoring.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.landtanin.teacherattendancemonitoring.R;
import com.landtanin.teacherattendancemonitoring.adapter.StudentListAdapter;
import com.landtanin.teacherattendancemonitoring.adapter.StudentListItem;
import com.landtanin.teacherattendancemonitoring.databinding.ActivityStudentListBinding;
import com.landtanin.teacherattendancemonitoring.fragment.FragmentStudent;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    ActivityStudentListBinding b;
    private StudentListAdapter mStudentListAdapter;
    private List<StudentListItem> mStudentListItems = new ArrayList<>();

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

        setSupportActionBar(b.studentListActivityToolbar);

    }



}
