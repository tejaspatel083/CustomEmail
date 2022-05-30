package com.example.customemail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customemail.Adapter.MyListAdapter;
import com.example.customemail.Model.MyDataList;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addAttachement, sendEmail;
    ExtendedFloatingActionButton addFab;

    Boolean isAllFabsVisible;

    private MyDataList[] myListData;
    Uri URI = null;
    private static final int RESULT_LOAD_IMAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        addFab = findViewById(R.id.add_fab);
        addAttachement = findViewById(R.id.add_attachement_fab);
        sendEmail = findViewById(R.id.send_fab);


        setFabVisibility();

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonCreateExcel(v);

                if (!isAllFabsVisible) {

                    addAttachement.show();
                    sendEmail.show();

                    addFab.extend();

                    isAllFabsVisible = true;
                } else {

                    addAttachement.hide();
                    sendEmail.hide();

                    addFab.shrink();

                    isAllFabsVisible = false;
                }
            }
        });

        addAttachement.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addAttachement();
                    }
                });

        sendEmail.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendEmail();
                    }
                });


        getEmpData();



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }

    private void getEmpData() {

        myListData = new MyDataList[]{
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
    }

    private void setFabVisibility() {

        addAttachement.setVisibility(View.GONE);
        sendEmail.setVisibility(View.GONE);

        isAllFabsVisible = false;

        addFab.shrink();
    }

    public void buttonCreateExcel(View view){



        Workbook wb=new HSSFWorkbook();
        Cell cell=null;
        CellStyle cellStyle=wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);

        Sheet sheet=null;
        sheet = wb.createSheet("Name of sheet");

        Row row =sheet.createRow(0);

        cell=row.createCell(0);
        cell.setCellValue("Employee ID");
        cell.setCellStyle(cellStyle);

        cell=row.createCell(1);
        cell.setCellValue("Employee Name");
        cell.setCellStyle(cellStyle);

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*200));


        for (int i = 0;i < myListData.length;i++)
        {
            Row row1 =sheet.createRow(i+1);

            cell=row1.createCell(0);
            cell.setCellValue(myListData[i].getEmpId());
            cell.setCellStyle(cellStyle);

            cell=row1.createCell(1);
            cell.setCellValue(myListData[i].getEmpName());
            cell.setCellStyle(cellStyle);

            sheet.setColumnWidth(0,(10*200));
            sheet.setColumnWidth(1,(10*200));

        }

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "EmployeeList.xls");

        FileOutputStream outputStream =null;

        try {
            outputStream=new FileOutputStream(file);
            wb.write(outputStream);

            Toast.makeText(getApplicationContext(),"Data exported to Excel",Toast.LENGTH_LONG).show();


        } catch (java.io.IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(),"Something Wrong",Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void addAttachement() {


        Intent chooseFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFileIntent.setType("*/*");
        chooseFileIntent.addCategory(Intent.CATEGORY_OPENABLE);

        chooseFileIntent = Intent.createChooser(chooseFileIntent, "Choose a file");
        startActivityForResult(chooseFileIntent, RESULT_LOAD_IMAGE);
    }

    private void sendEmail() {


        try
        {

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("*/*");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"tjp083@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Employee List");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Everyone!!");

            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));

        }
        catch (Throwable t) {
            Toast.makeText(this, "Request failed try again: "+ t.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            URI = data.getData();
            Toast.makeText(MainActivity.this, "Attachement Added", Toast.LENGTH_SHORT).show();
        }
    }

}
