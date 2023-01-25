package com.earl.productivityinside.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.earl.productivityinside.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}