package com.landtanin.teacherattendancemonitoring.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.landtanin.teacherattendancemonitoring.R;
import com.landtanin.teacherattendancemonitoring.dao.LecturerModuleDao;

import java.text.SimpleDateFormat;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by landtanin on 2/15/2017 AD.
 */

public class TimeTableListAdapter extends RealmRecyclerViewAdapter<LecturerModuleDao,RecyclerView.ViewHolder> {

    Context mContext;
    ClickListener mClickListener;
    int redColor, greenColor, greyColor, indegoColor, statusTxtGreenColor, blackColor;
    private String moduleId;
    private static final String TAG = "TimeTableListAdapter";

//    public TimeTableListAdapter(List<TimeTableListItem> moduleItemList, Context context) {
//        mtimeTableItemList = moduleItemList;
//        mContext = context;
//    }


//    public TimeTableListAdapter(List<TimeTableListItem> mtimeTableItemList, Context context, clickListener clickListener) {
//        this.mtimeTableItemList = mtimeTableItemList;
//        mContext = context;
//        mClickListener = clickListener;
//    }


    public TimeTableListAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<LecturerModuleDao> data, boolean autoUpdate, ClickListener clickListener) {
        super(context, data, autoUpdate);
        mContext = context;
        mClickListener = clickListener;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        redColor = ContextCompat.getColor(parent.getContext(), R.color.colorPink100);
        greenColor = ContextCompat.getColor(parent.getContext(), R.color.colorRealGreen100);
        statusTxtGreenColor = ContextCompat.getColor(parent.getContext(), R.color.colorActiveStatus);
        greyColor = ContextCompat.getColor(parent.getContext(), R.color.colorGrey500);
        indegoColor = ContextCompat.getColor(parent.getContext(), R.color.colorIndigo50);
        blackColor = ContextCompat.getColor(parent.getContext(), android.R.color.black);

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_time_table, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        RecyclerViewHolder itemHolder = (RecyclerViewHolder) holder; // it needs RecyclerViewHolder, not RecyclerView.ViewHolder
        LecturerModuleDao timeTableItem = getData().get(position);

        itemHolder.moduleNameTxt.setText(timeTableItem.getName());
        itemHolder.moduleIdTxt.setText(timeTableItem.getModuleId());
        itemHolder.statusTxt.setText(timeTableItem.getModStatus());

        if (timeTableItem.getModStatus().equals("active")) {
            itemHolder.itemView.setBackgroundColor(greenColor);
            itemHolder.statusTxt.setTextColor(statusTxtGreenColor);
        } else if (timeTableItem.getModStatus().equals("inactive")) {
            itemHolder.itemView.setBackgroundColor(indegoColor);
            itemHolder.statusTxt.setTextColor(blackColor);
        }

        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("HH:mm");
        String timeStr = currentTimeFormat.format(timeTableItem.getStartDate())
                + " - " + currentTimeFormat.format(timeTableItem.getEndDate());
        itemHolder.timeTxt.setText(timeStr);

        itemHolder.locationTxt.setText(timeTableItem.getRoom());

//        SharedPreferences prefs = Contextor.getInstance().getContext().getSharedPreferences("login_state", Context.MODE_PRIVATE);

        final LecturerModuleDao forClick = getData().get(position);
        final int positionOnclick = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moduleId = forClick.getModuleId();
                Log.w(TAG, "onBindViewHolder: moduleId = " + moduleId);
                mClickListener.myOnClickListener(moduleId, positionOnclick);

            }
        });

    }

//    @Override
//    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
//
////        final TimeTableListItem timeTableItem = mtimeTableItemList.get(position);
////
////        holder.moduleNameTxt.setText(timeTableItem.getModuleNameTxt());
////        holder.moduleIdTxt.setText(timeTableItem.getModuleIdTxt());
////        holder.statusTxt.setText(timeTableItem.getStatusTxt());
////        holder.timeTxt.setText(timeTableItem.getTimeTxt());
////        holder.locationTxt.setText(timeTableItem.getLocationTxt());
//
//
//
//    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView moduleNameTxt, moduleIdTxt, statusTxt, timeTxt, locationTxt;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            moduleNameTxt = (TextView) itemView.findViewById(R.id.moduleNameTxt);
            moduleIdTxt = (TextView) itemView.findViewById(R.id.moduleIdTxt);
            statusTxt = (TextView) itemView.findViewById(R.id.statusTxt);
            timeTxt = (TextView) itemView.findViewById(R.id.timeTxt);
            locationTxt = (TextView) itemView.findViewById(R.id.locationTxt);

        }
    }

    public interface ClickListener {
        void myOnClickListener(String moduleId, int itemNumber);
    }

}
