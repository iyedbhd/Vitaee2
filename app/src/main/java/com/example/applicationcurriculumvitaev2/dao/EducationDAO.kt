package com.example.applicationcurriculumvitaev2.dao

import androidx.room.*
import com.example.applicationcurriculumvitaev2.data.Education
import com.example.applicationcurriculumvitaev2.data.Experience
@Dao
interface EducationDAO {
    @Insert
    fun insert(item1:Education)

    @Update
    fun update(item1:Education)

    @Delete
    fun delete(item1:Education)

    @Query("SELECT * FROM educations")
    fun getAll(): List<Education>;
}