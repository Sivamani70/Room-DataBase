package com.example.roomdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpDateActivity extends AppCompatActivity {
    EditText name, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date);

        name = findViewById(R.id.updateName);
        id = findViewById(R.id.updateId);

        name.setText(getIntent().getStringExtra("name"));
        id.setText(getIntent().getStringExtra("rollNum"));

        id.setKeyListener(null);
    }

    public void save(View view) {
        String res_id = id.getText().toString();
        String res_name = name.getText().toString();

        StudentEntity entity = new StudentEntity();
        entity.setRollNum(res_id);
        entity.setName(res_name);
//        MainActivity.studentDataBase.studentDAO().updateData(entity);
        MainActivity.viewModel.update(entity);
        Toast.makeText(this, "Updated " + res_name, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}