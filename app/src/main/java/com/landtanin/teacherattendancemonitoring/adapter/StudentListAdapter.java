package com.landtanin.teacherattendancemonitoring.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public StudentListAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<StudentAttendanceDao> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
        mContext = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        RecyclerViewHolder itemHolder = (RecyclerViewHolder) holder; // it needs RecyclerViewHolder, not RecyclerView.ViewHolder
        final StudentAttendanceDao studentListItem = getData().get(position);

        itemHolder.studentNameTxt.setText(studentListItem.getName());
        itemHolder.studentIdTxt.setText(studentListItem.getStudentId());

//        if (timeTableItem.getModStatus().equals("active")) {
//            itemHolder.itemView.setBackgroundColor(greenColor);
//            itemHolder.statusTxt.setTextColor(statusTxtGreenColor);
//        } else if (timeTableItem.getModStatus().equals("inactive")) {
//            itemHolder.itemView.setBackgroundColor(indegoColor);
//            itemHolder.statusTxt.setTextColor(blackColor);
//        } else if (timeTableItem.getModStatus().equals("no more class")) {
//            itemHolder.itemView.setBackgroundColor(greyColor);
//            itemHolder.statusTxt.setTextColor(blackColor);
//        }

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
