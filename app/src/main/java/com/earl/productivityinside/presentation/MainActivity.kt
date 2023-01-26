package com.earl.productivityinside.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.earl.productivityinside.R

class MainActivity : AppCompatActivity(), NavigationContract {

    var progressBar: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragment()
    }

    override fun mainFragment() {
        showFragment(MainFragment.newInstance())
    }

    override fun showProgressBar() {
        progressBar = Dialog(this, android.R.style.Theme_Black)
        val view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null)
        progressBar!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressBar!!.window!!.setBackgroundDrawableResource(R.color.custom_transparent)
        progressBar!!.setContentView(view)
        progressBar!!.show()
    }

    override fun hideProgressBar() {
        progressBar?.dismiss()
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}