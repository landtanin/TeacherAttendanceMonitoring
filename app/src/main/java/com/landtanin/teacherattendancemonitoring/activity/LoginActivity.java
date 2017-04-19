package com.landtanin.teacherattendancemonitoring.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.landtanin.teacherattendancemonitoring.R;
import com.landtanin.teacherattendancemonitoring.databinding.ActivityLoginBinding;
import com.landtanin.teacherattendancemonitoring.fragment.FragmentLogin;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_login);

        initInstance();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.loginFragmentContentContainer,
                            FragmentLogin.newInstance(),
                            "LoginFragment")
                    .commit();

        }

    }

    private void initInstance() {


    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
