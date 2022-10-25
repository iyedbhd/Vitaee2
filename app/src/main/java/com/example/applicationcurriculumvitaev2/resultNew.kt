package com.example.applicationcurriculumvitaev2

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import com.example.applicationcurriculumvitaev2.databinding.ActivityMainBinding
import com.example.applicationcurriculumvitaev2.databinding.ActivityResultNewBinding

class resultNew : AppCompatActivity() {
    private lateinit var binding: ActivityResultNewBinding;

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.mLogout -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Logout")
                builder.setMessage("Do you really want to log out?")

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    run {
                        getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().clear().apply()
                        finish()
                    }
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->{}
                }

                builder.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionbar = supportActionBar
        actionbar!!.title = "Your resume"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);
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
        val img = intent.getStringExtra("img");
        println("my android skill is " + androidskill);
        if(img != "")
        {
            val imageBytes = Base64.decode(img, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            binding.profilePic2.setImageBitmap(decodedImage)
        }


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

        binding.careerBtn.setOnClickListener{
            val intent = Intent(this, careerActivity::class.java)
            startActivity(intent)

        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}