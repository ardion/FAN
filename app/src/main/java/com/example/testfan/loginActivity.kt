package com.example.testfan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.testfan.databinding.ActivityLoginBinding
import com.example.testfan.databinding.ActivityMainBinding

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = PreferenceHelper(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnlogin.setOnClickListener {

            if (binding.emaillogin.text.toString().isEmpty()) {
                binding.emaillogin.setError("email tidak boleh kosong")
            } else
                if (binding.pwlogin.text.toString().isEmpty()) {
                    binding.pwlogin.setError("password tidak boleh kosong")
                } else
                    if (isValidPassword(binding.pwlogin.text.toString())) {

                        if (sharedPref.getString(Constant.EMAIL).toString() == binding.emaillogin.text.toString()) {

                            if (sharedPref.getString(Constant.PASSWORD).toString() == binding.pwlogin.text.toString()) {
                                val intent = Intent(this, homeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                        applicationContext,
                                        "invalid password",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }

                        } else {
                            Toast.makeText(
                                    applicationContext,
                                    "invalid email",
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

        binding.fpw.setOnClickListener {
            val intent = Intent(this, forgotpwActivity::class.java)
            startActivity(intent)
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