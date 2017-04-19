package com.landtanin.teacherattendancemonitoring.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.landtanin.teacherattendancemonitoring.R;
import com.landtanin.teacherattendancemonitoring.databinding.FragmentMainBinding;
import com.landtanin.teacherattendancemonitoring.fragment.day.FridayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.MondayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.SaturdayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.SundayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.ThursdayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.TuesdayFragment;
import com.landtanin.teacherattendancemonitoring.fragment.day.WednesdayFragment;
import com.landtanin.teacherattendancemonitoring.manager.SmartFragmentStatePagerAdapter;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {

    FragmentMainBinding b;

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
