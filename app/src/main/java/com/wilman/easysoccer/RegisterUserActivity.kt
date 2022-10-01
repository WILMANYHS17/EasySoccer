package com.wilman.easysoccer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wilman.easysoccer.databinding.ActivityNavigationUserBinding
import com.wilman.easysoccer.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val typeUser = intent.extras!!.getString("user")
        if (typeUser == "Admin") {
            binding.txvNameTypeUser.text = getString(R.string.register_admin)
            binding.txvIdAdmin.visibility = View.VISIBLE
            binding.edtIdAdmin.visibility = View.VISIBLE
        } else {
            binding.txvNameTypeUser.text = getString(R.string.register_user)
        }
    }
}