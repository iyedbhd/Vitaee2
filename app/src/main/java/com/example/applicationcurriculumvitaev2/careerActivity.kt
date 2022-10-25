package com.example.applicationcurriculumvitaev2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import com.example.applicationcurriculumvitaev2.databinding.ActivityCareerBinding
import com.example.applicationcurriculumvitaev2.databinding.ActivityMainBinding
import com.example.applicationcurriculumvitaev2.databinding.ActivityResultNewBinding

class careerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareerBinding;
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.addExp -> {
                val intent = Intent(this, addExperienceActivity::class.java)

                startActivityForResult(intent, 1337)
            }
            R.id.addEduc -> {
                val intent = Intent(this, addEducation::class.java)

                startActivityForResult(intent, 1338)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareerBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar.appBar);
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
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == 1337) && (resultCode == 1337) ){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, CareerFragment.newInstance()).commit()
        }
        if ((requestCode == 1338) && (resultCode == 1338) ){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, CareerFragment2.newInstance()).commit()
        }
    }
}