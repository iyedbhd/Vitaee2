package com.example.applicationcurriculumvitaev2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experiences")

data class Experience (
    @PrimaryKey(autoGenerate = true)
    val id: Int,


    @ColumnInfo(name = "pic")
    val pic:String,


    @ColumnInfo(name = "companyName")
    val companyName:String,


    @ColumnInfo(name = "companyAddress")
    val companyAddress:String,

    @ColumnInfo(name = "startDate")
    val startDate:String,

    @ColumnInfo(name = "endDate")
    val endDate:String
)