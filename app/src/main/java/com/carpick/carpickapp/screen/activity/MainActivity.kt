package com.carpick.carpickapp.screen.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.carpick.carpickapp.R
import com.carpick.carpickapp.databinding.ActivityMainBinding
import com.carpick.carpickapp.screen.fragment.CarPickStartFragment
import com.carpick.carpickapp.screen.ComposeFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), ViewModelStoreOwner {
    interface BottomNavigationListener {
        fun showTextView()
    }

    private var listener : BottomNavigationListener?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeFragment(CarPickStartFragment())

        binding.navBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.car_recommend_fragment -> {
                    changeFragment(CarPickStartFragment())
                }

                R.id.car_ranking_fragment -> {
                    changeFragment(CarPickStartFragment())
                    listener?.showTextView()
                }
                R.id.car_poor_fragment -> {
                    changeFragment(CarPickStartFragment())
                    listener?.showTextView()
                }
            }
            true
        }
    }
    fun setSelectedTabListener(listener : BottomNavigationListener) {
        this.listener = listener
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