package com.example.customemail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.customemail.Adapter.MyListAdapter;
import com.example.customemail.Model.MyDataList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDataList[] myListData = new MyDataList[]{
                new MyDataList("1", "Tejas"),
                new MyDataList("2", "Sam"),
                new MyDataList("3", "Nilay"),
                new MyDataList("4", "Harsh"),
                new MyDataList("5", "Robert"),
                new MyDataList("6", "Prince"),
                new MyDataList("7", "Hardik"),
                new MyDataList("8", "Rahul"),
                new MyDataList("9", "Dinesh"),
                new MyDataList("10", "Sahil"),
                new MyDataList("11", "Jay"),
                new MyDataList("12", "Shivani"),
                new MyDataList("13", "Disha"),
                new MyDataList("14", "Tiger"),
                new MyDataList("15", "Priyanka"),
                new MyDataList("16", "Aamir"),
                new MyDataList("17", "Sachin"),
                new MyDataList("18", "Rohit"),
                new MyDataList("19", "Virat"),
                new MyDataList("20", "Anushka"),
        };


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
