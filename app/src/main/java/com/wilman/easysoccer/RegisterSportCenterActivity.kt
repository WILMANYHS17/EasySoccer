package com.wilman.easysoccer

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.wilman.easysoccer.databinding.ActivityRegisterSportCenterBinding
import com.wilman.easysoccer.databinding.ActivityRegisterUserBinding

class RegisterSportCenterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterSportCenterBinding
    private val database = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataSportCenter()
        binding.btnImageSportCenter.setOnClickListener { permmissionImage() }
        binding.btnRegisterSportCenter.setOnClickListener { registerSportCenter() }

    }

    fun registerSportCenter(){
        val nameCenter = binding.editTxtNameSportCenter.text
        val nit = binding.editTxtNitSportCenter.text
        val address = binding.editTxtDirectionSportCenter.text
        val description = binding.edtDescriptionCenter.text
        database.collection("users").document("whernandezsuesca@gmail.com")
            .collection("sportCenter").document(nit.toString()).set(
                hashMapOf(
                    "nameCenter" to nameCenter.toString(),
                    "nit" to nit.toString(),
                    "address" to address.toString(),
                    "description" to description.toString()
                )

            )
        onBackPressed()
    }

    fun getDataSportCenter(){

        database.collection("users").document("whernandezsuesca@gmail.com")
            .collection("sportCenter").document("1234ths").get().addOnSuccessListener {
                binding.editTxtNameSportCenter.setText(it.get("nameCenter") as String?)
                binding.editTxtNitSportCenter.setText(it.get("nit") as String)
                binding.edtDescriptionCenter.setText(it.get("description") as String?)
                binding.editTxtDirectionSportCenter.setText(it.get("address") as String?)

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
            binding.imageSportCenter.setImageURI(data)
        }


    }
    fun loadImage(){
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityResultGallery.launch(intent)
    }
}