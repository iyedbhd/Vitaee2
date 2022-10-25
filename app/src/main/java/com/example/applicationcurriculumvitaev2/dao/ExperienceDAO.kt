package com.example.applicationcurriculumvitaev2.dao

import androidx.room.*
import com.example.applicationcurriculumvitaev2.data.Education
import com.example.applicationcurriculumvitaev2.data.Experience
@Dao
interface ExperienceDAO {
    @Insert
    fun insert(item1:Experience)

    @Update
    fun update(item1:Experience)

    @Delete
    fun delete(item1:Experience)

    @Query("SELECT * FROM experiences")
    fun getAll(): List<Experience>;
}