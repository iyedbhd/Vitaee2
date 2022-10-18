package com.example.applicationcurriculumvitaev2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.example.applicationcurriculumvitaev2.databinding.ActivityCareerBinding
import com.example.applicationcurriculumvitaev2.databinding.ActivityMainBinding
import com.example.applicationcurriculumvitaev2.databinding.ActivityResultNewBinding

class careerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, CareerFragment.newInstance()).commit()

        binding.expBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, CareerFragment.newInstance()).commit()
        }

        binding.educationBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, CareerFragment2.newInstance()).commit()
        }
        // supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SkillsFragment.newInstance()).commit()

    }
}