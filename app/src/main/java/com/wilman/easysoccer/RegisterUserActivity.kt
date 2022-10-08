package com.wilman.easysoccer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.wilman.easysoccer.databinding.ActivityRegisterUserBinding

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
}