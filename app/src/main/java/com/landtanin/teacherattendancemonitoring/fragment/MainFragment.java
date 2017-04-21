package com.landtanin.teacherattendancemonitoring.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.landtanin.teacherattendancemonitoring.R;
import com.landtanin.teacherattendancemonitoring.dao.LecturerModuleDao;
import com.landtanin.teacherattendancemonitoring.dao.StudentAttendanceDao;
import com.landtanin.teacherattendancemonitoring.databinding.FragmentMainBinding;
import com.landtanin.teacherattendancemonitoring.fragment.day.FridayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.MondayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.SaturdayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.SundayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.ThursdayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.TuesdayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.WednesdayFragment;
import com.landtanin.teacherattendancemonitoring.manager.HttpManager;
import com.landtanin.teacherattendancemonitoring.manager.SmartFragmentStatePagerAdapter;
import com.landtanin.teacherattendancemonitoring.manager.TodayModule;
import com.landtanin.teacherattendancemonitoring.manager.http.ApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {

    FragmentMainBinding b;
    boolean run = true; //set it to false to stop the timer
    Handler mHandler = new Handler();
    private SimpleDateFormat timeFormat, dateFormat;
    private static final int STATUS_ACTIVE = 1;
    private static final int STATUS_INACTIVE = 2;
    private static final String TAG = "MainFragment";
    private int updateAllow = STATUS_INACTIVE;
    private int targetingModule = 1000;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View rootView = b.getRoot();
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        statusUpdate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    try {

                        Thread.sleep(20000);
                        // every 20 secs

                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {

                                statusUpdate();
                                Log.e(TAG, "run: ");

                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

        FragmentMainPagerAdapter fragmentMainPagerAdapter = new FragmentMainPagerAdapter(getChildFragmentManager());

        b.fragmentMainViewPager.setAdapter(fragmentMainPagerAdapter);
        b.fragMainTabLayout.setupWithViewPager(b.fragmentMainViewPager);
        for(int i = 0; i < b.fragMainTabLayout.getTabCount(); i++) {

            TabLayout.Tab tab = b.fragMainTabLayout.getTabAt(i);
            tab.setText(fragmentMainPagerAdapter.tabString[i] + "   ");

        }

        b.fragMainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void statusUpdate() {

        TodayModule todayModule = TodayModule.getInstance();
        RealmResults<LecturerModuleDao> lecturerModuleDao = todayModule.getTodayModule();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<StudentAttendanceDao> students = realm.where(StudentAttendanceDao.class).findAll();

        Calendar calendar = Calendar.getInstance();
        Date nowStatusUpdate = calendar.getTime();

        timeFormat = new SimpleDateFormat("HH:mm:ss");
//        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (lecturerModuleDao.size()!=0) {

            for (int i = 0; i<lecturerModuleDao.size(); i++) {

                int nowVSModuleStart = timeFormat.format(nowStatusUpdate)
                        .compareTo(timeFormat.format(lecturerModuleDao.get(i).getStartDate()));
                int nowVSModuleEnd = timeFormat.format(nowStatusUpdate)
                        .compareTo(timeFormat.format(lecturerModuleDao.get(i).getEndDate()));

                // module is not yet start
                if (nowVSModuleStart < 0) {

                    realmUpdateModStatus(i, lecturerModuleDao, STATUS_INACTIVE);
//                    updateAllow = STATUS_INACTIVE;

                }
                // start and not end
                else if (nowVSModuleStart>=0 && nowVSModuleEnd<0) {

                    realmUpdateModStatus(i, lecturerModuleDao, STATUS_ACTIVE);
                    targetingModule = i;

                }
                // end
                else if (nowVSModuleEnd>=0) {

                    Log.e(TAG, "statusUpdate: nowVSModuleEnd = " + nowVSModuleEnd);
                    Log.e(TAG, "statusUpdate: now = " + nowStatusUpdate);
                    Log.e(TAG, "statusUpdate: end time " + lecturerModuleDao.get(i).getName() + " = " + lecturerModuleDao.get(i).getEndDate());

                    realmUpdateModStatus(i, lecturerModuleDao, STATUS_INACTIVE);
                    // TODO update end status
//                    updateAllow = STATUS_INACTIVE

                    if (targetingModule==i) {

                        Log.d(TAG, "statusUpdate: updateAllow");
                        updateEndStatus(lecturerModuleDao, targetingModule);
                        targetingModule = 1000;


                    }

                }



            }

        }

    }

    private void updateEndStatus(RealmResults<LecturerModuleDao> lecturerModuleDao, int i) {

        Log.d(TAG, "updateEndStatus: ");
        ApiService apiService = HttpManager.getInstance().create(ApiService.class);
        apiService.attendanceUpdate("end",lecturerModuleDao.get(i).getModuleId())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void realmUpdateModStatus(int targetingModule, RealmResults<LecturerModuleDao> lecturerModuleDao, int status) {

//        updateEndStatus(studentModuleDao);
        // update to time table list adapter

        String modStatus = null;
        switch (status) {
            case STATUS_ACTIVE:
                modStatus = "active";
                break;
            case STATUS_INACTIVE:
                modStatus = "inactive";
                break;
            default:
                break;
        }

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(lecturerModuleDao).get(targetingModule).setModStatus(modStatus);
        realm.commitTransaction();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

    public class FragmentMainPagerAdapter extends SmartFragmentStatePagerAdapter {

        private SmartFragmentStatePagerAdapter adapterViewPager;

        public String[] tabString = getResources().getStringArray(R.array.date);
        public FragmentMainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MondayFragment.newInstance();
                case 1:
                    return TuesdayFragment.newInstance();
                case 2:
                    return WednesdayFragment.newInstance();
                case 3:
                    return ThursdayFragment.newInstance();
                case 4:
                    return FridayFragment.newInstance();
                case 5:
                    return SaturdayFragment.newInstance();
                case 6:
                    return SundayFragment.newInstance();
                default:
                    return MainFragment.newInstance();
            }
        }

        @Override
        public int getCount() {

            return tabString.length;

        }
    }

}
