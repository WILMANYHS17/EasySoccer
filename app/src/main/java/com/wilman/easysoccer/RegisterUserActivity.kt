package com.wilman.easysoccer
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.wilman.easysoccer.databinding.ActivityRegisterUserBinding
import com.wilman.easysoccer.ui.calendarUser.DatePickerFragment

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
    private val dataBase = FirebaseFirestore.getInstance()
    private lateinit var typeUser: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        typeUser = intent.extras!!.getString("user") ?: ""
        if (typeUser == "Admin") {
            binding.txvNameTypeUser.text = getString(R.string.register_admin)
            binding.txvIdAdmin.visibility = View.VISIBLE
            binding.edtIdAdmin.visibility = View.VISIBLE
        } else {
            binding.txvNameTypeUser.text = getString(R.string.register_user)
        }
        binding.btnRegisterUser.setOnClickListener {
            registerUser()
        }
        binding.btnAddImage.setOnClickListener { permmissionImage() }
        binding.edtAgeUser.setOnClickListener {  showDatePickerDialog()}
    }

    private fun registerUser() {
        val email = binding.edtEmailUser.text
        val password = binding.edtPasswordUser.text
        val name = binding.edtNameUser.text
        val userName = binding.edtUserName.text
        val numberId = binding.edtIdAdmin.text
        val age = binding.edtAgeUser.text
        val numberPhone = binding.edtPhoneNumber.text
        email?.let {
            dataBase.collection("users").document(it.toString()).set(
                hashMapOf(
                    "identification" to numberId.toString(),
                    "name" to name.toString(),
                    "userName" to userName.toString(),
                    "password" to password.toString(),
                    "age" to age.toString(),
                    "numberPhone" to numberPhone.toString(),
                    "isAdmin" to (typeUser == "Admin")
                )
            )
            onBackPressed()
        }
    }
    fun permmissionImage(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    loadImage()
                }
                else->requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }


        }else{

            loadImage()
        }
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/")


    }
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted ->
        if(isGranted){
            loadImage()
        }else{
            Toast.makeText(this, "Habilitar permisos para continuar", Toast.LENGTH_SHORT).show()
        }

    }
    private val startActivityResultGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data = result.data?.data
            binding.imageUser.setImageURI(data)
        }


    }
    fun loadImage(){
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityResultGallery.launch(intent)
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.edtAgeUser.setText("$day / $month / $year")
    }

}