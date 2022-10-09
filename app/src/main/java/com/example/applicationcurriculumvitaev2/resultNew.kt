package com.example.applicationcurriculumvitaev2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.example.applicationcurriculumvitaev2.databinding.ActivityMainBinding
import com.example.applicationcurriculumvitaev2.databinding.ActivityResultNewBinding

class resultNew : AppCompatActivity() {
    private lateinit var binding: ActivityResultNewBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultNewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        val FullName = intent.getStringExtra("Name")
        val age = intent.getStringExtra("Age")
        val mail = intent.getStringExtra("Email")
        val gender= intent.getStringExtra("Gender")
        val androidskill=intent.getIntExtra("Android",0);
        val iosSkill = intent.getIntExtra("iOS",0);
        val flutterskill = intent.getIntExtra("Flutter",0);
        val language = intent.getStringExtra("Language")
        val hbs = intent.getStringExtra("Hobbies")
        val img = Uri.parse(intent.getStringExtra("img"));
        println("my android skill is " + androidskill);
        binding.profilePic2.setImageURI(img)


        binding.nameTXT.setText(FullName);
        binding.emailTXT.setText(mail);

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SkillsFragment.newInstance(androidskill, iosSkill, flutterskill)).commit()

        binding.HobbiesBtn.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, Hobbies.newInstance(hbs)).commit()
        }
        binding.SkillsBtn.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SkillsFragment.newInstance(androidskill, iosSkill, flutterskill)).commit()
        }
        binding.LangBtn.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, LanguageFragment.newInstance(language)).commit()
        }
    }
}