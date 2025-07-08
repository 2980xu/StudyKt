package com.example.myapplication.aj.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(StudentRoom studentRoom);

    @Delete
    void deleteStudent(StudentRoom studentRoom);

    @Update
    void updateStudent(StudentRoom studentRoom);

    @Query("SELECT * FROM student")
    List<StudentRoom> getStudentList();

    @Query("SELECT * FROM student WHERE id = :id")
    StudentRoom getStudentById(int id);
}
