package com.example.testfan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.testfan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sharedPref = PreferenceHelper(this)

        binding.btnsignup.setOnClickListener {
            sharedPref.put(Constant.NAME_USER, binding.nama.text.toString())
            sharedPref.put(Constant.EMAIL, binding.email.text.toString())
            sharedPref.put(Constant.PASSWORD, binding.pw.text.toString())
            sharedPref.put(Constant.REPASSWORD, binding.pwre.text.toString())

            if (binding.nama.text.toString().isEmpty()) {
                binding.nama.setError("nama tidak boleh kosong")
            } else if (binding.nama.text.length < 3) {
                binding.nama.setError("nama minimal 3 huruf")
            } else if (binding.email.text.toString().isEmpty()) {
                binding.email.setError("email tidak boleh kosong")
            } else if (binding.pw.text.toString().isEmpty()) {
                binding.pw.setError("password tidak boleh kosong")
            } else if (binding.pwre.text.toString().isEmpty()) {
                binding.pwre.setError("password tidak boleh kosong")
            } else if (isValidPassword(binding.pw.text.toString())) {
                if (binding.pw.text.toString() == binding.pwre.text.toString()) {
                    val intentregis = Intent(this, loginActivity::class.java)
                    startActivity(intentregis)
                } else {
                    Toast.makeText(
                            applicationContext,
                            "password tidak sama",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                        applicationContext,
                        "password salah",
                        Toast.LENGTH_SHORT
                ).show()
            }

        }
    }


    fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+\$).{8,}"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }
}