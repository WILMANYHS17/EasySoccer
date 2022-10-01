package com.wilman.easysoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wilman.easysoccer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInitSession.setOnClickListener { onClickInitSession() }
        binding.btnRegisterUser.setOnClickListener { onClickRegisterUser() }
        binding.btnRegisterAdmin.setOnClickListener { onClickRegisterAdmin() }
    }

    private fun onClickRegisterAdmin() {
        val intent = Intent(this, RegisterAdminActivity::class.java)
        startActivity(intent)
    }

    private fun onClickRegisterUser() {
        val intent = Intent(this, RegisterUserActivity::class.java)
        startActivity(intent)
    }

    private fun onClickInitSession() {
        if (binding.usuario.text?.isNotEmpty() == true && binding.contrasena.text?.isNotEmpty() == true) {
            val intent = Intent(this, NavigatorAdminActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}