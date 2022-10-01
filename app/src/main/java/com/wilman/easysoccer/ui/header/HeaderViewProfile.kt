package com.wilman.easysoccer.ui.header


import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.wilman.easysoccer.R
import com.wilman.easysoccer.RegisterUserActivity
import com.wilman.easysoccer.databinding.HeaderProfileBinding

class HeaderViewProfile(val binding: HeaderProfileBinding, val fragment: Fragment) {

    fun build() {
        binding.imvArrow.setOnClickListener {
            fragment.requireActivity().onBackPressed()
        }
        binding.txvUserName.text =
            "${fragment.getString(R.string.hello_name_user)} Jhonatan Alexander Rojas Suesca"

        binding.imvUser.setOnClickListener {
            val intent = Intent(fragment.activity, RegisterUserActivity::class.java)
            intent.putExtra("user", "User")
            startActivity(fragment.context!!, intent, null)
        }

    }
}