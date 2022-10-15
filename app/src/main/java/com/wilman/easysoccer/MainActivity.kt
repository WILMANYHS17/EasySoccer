package com.wilman.easysoccer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.wilman.easysoccer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dataBase = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.btnInitSession.setOnClickListener { onClickInitSession() }
        binding.btnRegisterUser.setOnClickListener { onClickRegisterUser() }
        binding.btnRegisterAdmin.setOnClickListener { onClickRegisterAdmin() }
    }

    private fun onClickRegisterAdmin() {
        val intent = Intent(this, RegisterUserActivity::class.java)
        intent.putExtra("user", "Admin")
        startActivity(intent)
    }

    private fun onClickRegisterUser() {
        val intent = Intent(this, RegisterUserActivity::class.java)
        intent.putExtra("user", "User")
        startActivity(intent)
    }

    private fun onClickInitSession() {
        if (binding.usuario.text?.isNotEmpty() == true && binding.contrasena.text?.isNotEmpty() == true) {
            val email = binding.usuario.text.toString()
            val contrasena = binding.contrasena.text.toString()
            dataBase.collection("users").document(email).get().addOnSuccessListener {
                if (contrasena == it.get("password")) {
                    if (it.get("isAdmin") as Boolean) {
                        val intent = Intent(this, NavigatorAdminActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, NavigationUserActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Email o Contraseña incorrectos", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}

