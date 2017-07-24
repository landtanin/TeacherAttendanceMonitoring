package com.landtanin.teacherattendancemonitoring.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.landtanin.teacherattendancemonitoring.R;
import com.landtanin.teacherattendancemonitoring.dao.StudentAttendanceDao;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by landtanin on 2/15/2017 AD.
 */

public class StudentListAdapter extends RealmRecyclerViewAdapter<StudentAttendanceDao,RecyclerView.ViewHolder>{

    Context mContext;
    int redColor, greenColor, greyColor, indegoColor, statusTxtGreenColor, blackColor, orangeColor;

    public StudentListAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<StudentAttendanceDao> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
        mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        redColor = ContextCompat.getColor(parent.getContext(), R.color.colorPink100);
        greenColor = ContextCompat.getColor(parent.getContext(), R.color.colorRealGreen100);
        orangeColor = ContextCompat.getColor(parent.getContext(), R.color.colorOrange100);
        statusTxtGreenColor = ContextCompat.getColor(parent.getContext(), R.color.colorActiveStatus);
        greyColor = ContextCompat.getColor(parent.getContext(), R.color.colorGrey500);
        indegoColor = ContextCompat.getColor(parent.getContext(), R.color.colorIndigo50);
        blackColor = ContextCompat.getColor(parent.getContext(), android.R.color.black);


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getData().size()!=0) {
            RecyclerViewHolder itemHolder = (RecyclerViewHolder) holder; // it needs RecyclerViewHolder, not RecyclerView.ViewHolder
            StudentAttendanceDao studentListItem = getData().get(position);

            itemHolder.studentNameTxt.setText(studentListItem.getName());
            itemHolder.studentIdTxt.setText(studentListItem.getStudentId());

            if (studentListItem.getAttendanceStatus().equals("checked")) {
                itemHolder.itemView.setBackgroundColor(greenColor);
            } else if (studentListItem.getAttendanceStatus().equals("late")) {
                itemHolder.itemView.setBackgroundColor(orangeColor);
            } else if (studentListItem.getAttendanceStatus().equals("end")) {
                itemHolder.itemView.setBackgroundColor(indegoColor);
            }

        }

    }

    @Override
    public int getItemCount() {
        return getData().size();
    }



    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView studentNameTxt, studentIdTxt;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            studentNameTxt = (TextView) itemView.findViewById(R.id.studentNameTxt);
            studentIdTxt = (TextView) itemView.findViewById(R.id.studemtIdTxt);

        }
    }


}
