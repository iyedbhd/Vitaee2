package tn.esprit.loldatastorage.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.applicationcurriculumvitaev2.addEducation
import com.example.applicationcurriculumvitaev2.dao.EducationDAO
import com.example.applicationcurriculumvitaev2.dao.ExperienceDAO
import com.example.applicationcurriculumvitaev2.data.Education
import com.example.applicationcurriculumvitaev2.data.Experience

//TODO 8 "Create the AppDataBase class"
@Database(entities = [Experience::class, Education::class], version = 2)
abstract class AppDataBase : RoomDatabase() {

    abstract fun expDAO(): ExperienceDAO
    abstract fun eduDAO(): EducationDAO

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, AppDataBase::class.java, "vitaee2")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return instance!!
        }
    }
}

