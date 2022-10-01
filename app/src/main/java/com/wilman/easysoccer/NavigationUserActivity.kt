package com.wilman.easysoccer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wilman.easysoccer.databinding.ActivityNavigationUserBinding

class NavigationUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val navView: BottomNavigationView = binding.navViewUser

        val navController = findNavController(R.id.nav_host_fragment_activity_navigator_user)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_user, R.id.navigation_reserve_user
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}