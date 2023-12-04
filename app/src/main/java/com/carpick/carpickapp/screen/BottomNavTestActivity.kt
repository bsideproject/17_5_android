package com.carpick.carpickapp.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.carpick.carpickapp.R

import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_nav_test)

        var nav = findViewById<BottomNavigationView>(R.id.nav_bar)

        nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.AFragment -> {
                    changeFragment(AFragment())
                }
                R.id.BFragment -> {
                    changeFragment(ComposeFragment())
                }
            }
            true
        }
    }
    private fun changeFragment(fragment: Fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.nav_host, fragment)
            .commit()
    }
}