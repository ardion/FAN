package com.example.testfan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.testfan.databinding.ActivityForgotpwBinding
import com.example.testfan.databinding.ActivityHomeBinding

class forgotpwActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgotpwBinding
    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgotpw)
        sharedPref = PreferenceHelper(this)
        binding.btnresetpw.setOnClickListener {
            sharedPref.put(Constant.PASSWORD, binding.newpw.text.toString())
            sharedPref.put(Constant.REPASSWORD, binding.confirmnewpw.text.toString())

            if (binding.newpw.text.toString().isEmpty()) {
                binding.newpw.setError("password tidak boleh kosong")
            } else
                if (binding.confirmnewpw.text.toString().isEmpty()) {
                    binding.confirmnewpw.setError("password tidak boleh kosong")
                } else

                    if (isValidPassword(binding.newpw.text.toString())) {
                        if (binding.newpw.text.toString() == binding.confirmnewpw.text.toString()) {
                            val intentregis = Intent(this, loginActivity::class.java)
                            startActivity(intentregis)
                        } else {
                            Toast.makeText(
                                    applicationContext,
                                    "pasword tidak sama",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }  else {
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