package com.example.roomdata;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepo {

    LiveData<List<StudentEntity>> listLiveData;
    StudentDataBase studentDataBase;

    public StudentRepo(Application application) {
        studentDataBase = StudentDataBase.getDataBase(application);
        listLiveData = studentDataBase.studentDAO().retriveData();
    }

    public void insert(StudentEntity entity) {
        new MyAsyncTakForInsert().execute(entity);
    }

    public void update(StudentEntity entity) {
        new MyAsyncTakForUpdate().execute(entity);
    }

    public void delete(StudentEntity entity) {
        new MyAsyncTaskDelete().execute(entity);
    }

    //for live data inserting
    public class MyAsyncTakForInsert extends AsyncTask<StudentEntity, Void, Void> {

        @Override
        protected Void doInBackground(StudentEntity... student_entities) {
            studentDataBase.studentDAO().insertData(student_entities[0]);
            return null;
        }
    }

    //for live data update
    public class MyAsyncTakForUpdate extends AsyncTask<StudentEntity, Void, Void> {

        @Override
        protected Void doInBackground(StudentEntity... student_entities) {
            studentDataBase.studentDAO().updateData(student_entities[0]);
            return null;
        }
    }

    //for live data delete
    public class MyAsyncTaskDelete extends AsyncTask<StudentEntity, Void, Void> {

        @Override
        protected Void doInBackground(StudentEntity... student_entities) {
            studentDataBase.studentDAO().deleteData(student_entities[0]);
            return null;
        }
    }
}
