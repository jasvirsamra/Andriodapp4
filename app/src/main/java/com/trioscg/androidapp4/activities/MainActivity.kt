package com.trioscg.androidapp4.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.trioscg.androidapp4.R
import com.trioscg.androidapp4.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.trioscg.androidapp4.R.layout.activity_main)
        loadFragment(HomeFragment())
    }

    fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
