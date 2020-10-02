package com.example.roomdata;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentEntity {
    @ColumnInfo(name = "StudentName")
    String name;

    @ColumnInfo(name = "StudentRollNum")
    @PrimaryKey
    @NonNull
    String rollNum;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(@NonNull String rollNum) {
        this.rollNum = rollNum;
    }
}
