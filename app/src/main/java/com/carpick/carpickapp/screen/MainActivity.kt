package com.carpick.carpickapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var nav = findViewById<BottomNavigationView>(R.id.nav_bar)

        nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.car_recommend_fragment -> {
                    changeFragment(AFragment())
                }
                R.id.car_wishlist_fragment -> {
                    changeFragment(ComposeFragment())
                }
                R.id.car_ranking_fragment -> {
                    changeFragment(ComposeFragment())
                }
                R.id.car_poor_fragment -> {
                    changeFragment(AFragment())
                }
            }
            true
        }
    }
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host, fragment)
            .commit()
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}