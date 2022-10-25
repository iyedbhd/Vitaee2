package com.example.applicationcurriculumvitaev2

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.doOnTextChanged
import com.example.applicationcurriculumvitaev2.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var encodedImage:String = "";
    private lateinit var binding: ActivityMainBinding;

    lateinit var mSharedPref: SharedPreferences;


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = findViewById<TextView>(R.id.NameInput)
        val outlineName = findViewById<TextInputLayout>(R.id.outlined_username)
        val email = findViewById<TextView>(R.id.emailInput)
        val outlineEmail = findViewById<TextInputLayout>(R.id.outlined_email)
        val age = findViewById<TextView>(R.id.ageInput)
        val outlineAge = findViewById<TextInputLayout>(R.id.outlined_age)
        val next = findViewById<Button>(R.id.next)
        val male = findViewById<RadioButton>(R.id.genre_homme)
        val female = findViewById<RadioButton>(R.id.genre_femme)
        val GenderGroup = findViewById<RadioGroup>(R.id.GenderGroup)
        val groupe = GenderGroup.checkedRadioButtonId
        val FM = findViewById<RadioButton>(groupe)
        println("Hello&goodbye " + binding.profilePic);
        Log.d("Test", "Hello World");
        name.setOnClickListener {
            Log.d("Test", "I Click Name");

            print("clicked name");
        }
        binding.profilePic.setOnClickListener {
            Log.d("Test", "I Click Pic");

            print("clicked");
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }


        when {
            email.text.isEmpty() -> next.isEnabled = false
            age.text.isEmpty() -> next.isEnabled = false
            name.text.isEmpty() -> next.isEnabled = false

        }
        name.doOnTextChanged { text, start, before, count ->
            if (text!!.isEmpty()) {
                outlineName.error = "Must not be empty!"
                next.isEnabled = false
            } else {
                outlineName.error = null
                next.isEnabled = !(email.text.isEmpty() && age.text.isEmpty())
            }
        }
        email.doOnTextChanged { text, start, before, count ->
            if (Patterns.EMAIL_ADDRESS.matcher(email.text.toString())
                    .matches()
            ) {
                next.isEnabled = true
                outlineName.error = null
            }
            else if (text!!.isEmpty()) {
                outlineEmail.error = "Must not be empty!"
                next.isEnabled=false
            }
            else {
                outlineEmail.error = null
                email.setError("Check your mail")
                next.isEnabled = !(age.text.isEmpty() && name.text.isEmpty())


            }
        }
        age.doOnTextChanged { text, start, before, count ->
            if (text!!.isEmpty()) {
                outlineAge.error = "Must not be empty!"
                next.isEnabled = false
            } else if (text.length >= 3) {
                outlineAge.error = "Age is wrong!"
                next.isEnabled = false
            } else {
                outlineAge.error = null
                next.isEnabled = !(email.text.isEmpty() && name.text.isEmpty())
            }
        }
        /*
        val intent = Intent(this, careerActivity::class.java)
        startActivity(intent)*/
        next.setOnClickListener {


            val fullname = name.text.toString()
            val ageV2 = age.text.toString()
            val mail = email.text.toString()
            val genre = FM.text.toString()

            val intent = Intent(this, SkillHobbiesActivity::class.java)
            //val intent = Intent(this, resultNew::class.java)
            intent.putExtra("Name", fullname)
            intent.putExtra("Email", mail)
            intent.putExtra("Age", ageV2)
            intent.putExtra("Gender", genre)
            intent.putExtra("img", encodedImage);
            startActivity(intent)

        }


        if (mSharedPref.getBoolean("remembered", false)){
            val intent = Intent(this, resultNew::class.java)
            intent.putExtra("Android", mSharedPref.getInt("android", 0))
            intent.putExtra("iOS", mSharedPref.getInt("iOS", 0))
            intent.putExtra("Flutter", mSharedPref.getInt("flutter", 0))
            intent.putExtra("Language", mSharedPref.getString("lang", ""))
            intent.putExtra("Hobbies", mSharedPref.getString("hb", ""))
            intent.putExtra("Name", mSharedPref.getString("name", ""))
            intent.putExtra("Age", mSharedPref.getString("lang", ""))
            intent.putExtra("Email", mSharedPref.getString("mail", ""))
            intent.putExtra("Gender", mSharedPref.getString("genre", ""))
            intent.putExtra("img", mSharedPref.getString("img", ""))
            startActivity(intent)
            return;
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            binding.profilePic.setImageURI(imageUri)
            val baos = ByteArrayOutputStream()
            val bitmap = binding.profilePic.drawable.toBitmap()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
            with(mSharedPref.edit())
            {
                putString("img", encodedImage)
                apply()
            }
        }
    }

}
