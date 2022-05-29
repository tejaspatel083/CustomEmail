package com.example.customemail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.customemail.Adapter.MyListAdapter;
import com.example.customemail.Model.MyDataList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnEmail;
    private MyDataList[] myListData;
    //private File filePath = new File(Environment.getExternalStorageDirectory() + "/Demo.xls");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        btnEmail = findViewById(R.id.buttonEmail);

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


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


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

        File file = new File(getExternalFilesDir(null),"plik.xls");
        FileOutputStream outputStream =null;

        try {
            outputStream=new FileOutputStream(file);
            wb.write(outputStream);
            Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(),"NO OK",Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
