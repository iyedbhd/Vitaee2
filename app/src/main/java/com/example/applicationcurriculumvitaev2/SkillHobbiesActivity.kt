package com.example.applicationcurriculumvitaev2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
const val PREF_NAME = "Vitaee2"

class SkillHobbiesActivity : AppCompatActivity() {
    lateinit var mSharedPref: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill_hobbies)
        val androidSkill = findViewById<SeekBar>(R.id.seekBar_android)
        val iosSkill = findViewById<SeekBar>(R.id.seekBar_ios)
        val flutterSkill = findViewById<SeekBar>(R.id.seekBar_flutter)
        val arabic = findViewById<CheckBox>(R.id.checkBox_arabic)
        val french = findViewById<CheckBox>(R.id.checkbox_french)
        val english = findViewById<CheckBox>(R.id.checkBox_english)
        val music = findViewById<CheckBox>(R.id.checkbox_arabic)
        val sport = findViewById<CheckBox>(R.id.checkbox_fr)
        val games = findViewById<CheckBox>(R.id.checkbox_eng)
        val rememberMe = findViewById<CheckBox>(R.id.rememberMe)
        val submit = findViewById<Button>(R.id.SUBMIT)

        val intent = intent
        val name = intent.getStringExtra("Name")
        val age = intent.getStringExtra("Age")
        val mail = intent.getStringExtra("Email")
        val genre = intent.getStringExtra("Gender")
        val img = intent.getStringExtra("img");

        submit.setOnClickListener {


            val intent = Intent(this, resultNew::class.java)
            val lang = when {
                arabic.isChecked && french.isChecked && english.isChecked -> "Arabic French English"
                arabic.isChecked && french.isChecked -> "Arabic French"
                arabic.isChecked && english.isChecked -> "Arabic English"
                french.isChecked && english.isChecked -> "French English"
                arabic.isChecked -> "Arabic"
                french.isChecked -> "French"
                english.isChecked -> "English"

                else -> "None"
            }
            val hb = when {
                music.isChecked && sport.isChecked && games.isChecked -> "Music Sport Games"
                sport.isChecked && games.isChecked -> "Sport Games"
                sport.isChecked && music.isChecked -> "Sport Music"
                games.isChecked && music.isChecked -> "Games Music"
                games.isChecked -> "Games"
                sport.isChecked -> "Sport"
                music.isChecked -> "Music"
                else -> "None"
            }

            intent.putExtra("Android", androidSkill.progress)
            intent.putExtra("iOS", iosSkill.progress)
            intent.putExtra("Flutter", flutterSkill.progress)
            intent.putExtra("Language", lang)
            intent.putExtra("Hobbies", hb)
            intent.putExtra("Name", name)
            intent.putExtra("Age", age)
            intent.putExtra("Email", mail)
            intent.putExtra("Gender", genre)
            intent.putExtra("img", img)
            startActivity(intent)

            if(rememberMe.isChecked)
            {
                //TODO 4 "Edit the SharedPreferences by putting all the data"
                mSharedPref.edit().apply{
                    putBoolean("remembered", true)
                    putString("name", name)
                    putString("mail", age)
                    putString("genre", genre)
                    putString("img", img)
                    putString("lang", lang)
                    putString("hb", hb)
                    putInt("android", androidSkill.progress)
                    putInt("iOS", iosSkill.progress)
                    putInt("flutter", flutterSkill.progress)
                }.apply()


            }else{
                mSharedPref.edit().clear().apply()
            }
        }
    }


}