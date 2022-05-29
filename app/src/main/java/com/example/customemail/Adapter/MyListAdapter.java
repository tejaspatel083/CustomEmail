package com.example.customemail.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customemail.Model.MyDataList;
import com.example.customemail.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {


    private MyDataList[] listData;

    public MyListAdapter(MyDataList[] listData) {
        this.listData = listData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MyDataList myDataList = listData[position];
        holder.empId.setText(listData[position].getEmpId());
        holder.empName.setText(listData[position].getEmpName());
    }


    @Override
    public int getItemCount() {
        return listData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         public TextView empId,empName;

         ViewHolder(View itemView) {
            super(itemView);

             empId = itemView.findViewById(R.id.empId);
             empName = itemView.findViewById(R.id.empName);
        }
    }
}
