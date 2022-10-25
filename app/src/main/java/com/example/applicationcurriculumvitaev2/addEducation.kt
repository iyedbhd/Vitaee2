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
import com.example.applicationcurriculumvitaev2.data.Education
import com.example.applicationcurriculumvitaev2.data.Experience
import com.example.applicationcurriculumvitaev2.databinding.ActivityAddExpBinding
import com.example.applicationcurriculumvitaev2.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.loldatastorage.utils.AppDataBase
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class addEducation : AppCompatActivity() {
    private val pickImage = 200
    private var encodedImage:String = "";
    private lateinit var binding: ActivityAddExpBinding;
    private var imageUri: Uri? = null

    lateinit var dataBase : AppDataBase


    fun doChecks()
    {
        binding.next.isEnabled = true;
        when {
            binding.companyNameInput.text?.isEmpty() == true -> binding.next.isEnabled = false
            binding.companyAddressInput.text?.isEmpty() == true -> binding.next.isEnabled = false
            binding.startdateInput.text?.isEmpty() == true -> binding.next.isEnabled = false
            binding.enddate.text?.isEmpty() == true -> binding.next.isEnabled = false
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        dataBase = AppDataBase.getDatabase(this)

        val actionbar = supportActionBar
        actionbar!!.title = "Add Education"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24);


        binding = ActivityAddExpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        val datePicker2 =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        binding.startdateInput.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus)datePicker.show(supportFragmentManager, "DatePicker")
        }
        binding.enddate.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus)datePicker2.show(supportFragmentManager, "DatePicker")
        }


        datePicker.addOnPositiveButtonClickListener {
            // formatting date in dd-mm-yyyy format.
            val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
            val date = dateFormatter.format(Date(it))
            binding.startdateInput.setText(date);
            doChecks();

        }
        datePicker2.addOnPositiveButtonClickListener {
            // formatting date in dd-mm-yyyy format.
            val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
            val date = dateFormatter.format(Date(it))
            binding.enddate.setText(date);
            doChecks();

        }
        binding.pic.setOnClickListener {
            print("clicked");
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }


        binding.companyNameInput.doOnTextChanged { text, start, before, count ->
            if (text!!.isEmpty()) {
                binding.outlinedCompanyName.error = "Must not be empty!"
                doChecks();
            } else {
                binding.outlinedCompanyName.error = null
                doChecks();
            }
        }

        /*
        val intent = Intent(this, careerActivity::class.java)
        startActivity(intent)*/
        binding.next.setOnClickListener {

            dataBase.eduDAO().insert(Education(0, encodedImage, binding.companyNameInput.text.toString(), binding.companyAddressInput.text.toString(), binding.startdateInput.text.toString(), binding.enddate.text.toString()));
            setResult(1337)

            finish();

        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        print("wazupp")

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            binding.pic.setImageURI(imageUri)
            val baos = ByteArrayOutputStream()
            val bitmap = binding.pic.drawable.toBitmap()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)


        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
