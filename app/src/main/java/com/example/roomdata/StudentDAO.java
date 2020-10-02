package com.example.roomdata;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    public void insertData(StudentEntity entity);

    @Update
    public void updateData(StudentEntity entity);

    @Delete
    public void deleteData(StudentEntity entity);

    //for live data
    @Query("SELECT * FROM student_table")
    public LiveData<List<StudentEntity>> retriveData();
//    public List<StudentEntity> retriveData();   //Normal data
}
