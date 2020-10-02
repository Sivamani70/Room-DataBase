package com.example.roomdata;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static StudentDataBase studentDataBase;
    static StudentViewModel viewModel;
    EditText stuNameET, stuIdET;
    StudentAdapter adapter;
    StudentEntity studentEntity;
    List<StudentEntity> entityList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        stuIdET = findViewById(R.id.id);
        stuNameET = findViewById(R.id.name);
        recyclerView = findViewById(R.id.recycler);


//        studentDataBase = Room.databaseBuilder(this, StudentDataBase.class, "MyDB").allowMainThreadQueries().build();
        viewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        viewModel.liveData().observe(this, new Observer<List<StudentEntity>>() {
            @Override
            public void onChanged(List<StudentEntity> studentEntities) {
                adapter = new StudentAdapter(MainActivity.this, studentEntities);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adapter);

            }
        });


    }

    public void save(View view) {

        final String studentId = stuIdET.getText().toString();
        final String studentName = stuNameET.getText().toString();
        if (!studentId.isEmpty() && !studentName.isEmpty()) {
            studentEntity = new StudentEntity();
            studentEntity.setRollNum(studentId);
            studentEntity.setName(studentName);
//            studentDataBase.studentDAO().insertData(studentEntity);
            viewModel.insert(studentEntity);
            Toast.makeText(this, "Added Successfully" + studentId + studentName, Toast.LENGTH_SHORT).show();
            stuIdET.setText("");
            stuNameET.setText("");
            return;
        }
        stuNameET.setError("Enter Name");
        stuIdET.setError("Enter RollNum");
    }

//    public void retrive(View view) {
//        entityList = studentDataBase.studentDAO().retriveData();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new StudentAdapter(this, entityList);
//        recyclerView.setAdapter(adapter);
//    }
}