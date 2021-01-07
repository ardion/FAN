package com.example.testfan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.testfan.databinding.ActivityHomeBinding
import com.example.testfan.databinding.ActivityMainBinding

class homeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        sharedPref = PreferenceHelper(this)
        binding.twname.text=sharedPref.getString(Constant.NAME_USER).toString()
        binding.mailo.text=sharedPref.getString(Constant.EMAIL).toString()
        binding.pass.text=sharedPref.getString(Constant.PASSWORD).toString()
    }
}