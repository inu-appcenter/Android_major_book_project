package com.example.majorbookservice.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.majorbookservice.CreateAccountActivity
import com.example.majorbookservice.Data.RetrofitService
import com.example.majorbookservice.databinding.ActivityLogInBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            val nextIntent = Intent(this, CreateAccountActivity::class.java)
            startActivity(nextIntent)
        }

        var id = binding.idInputEditText.toString()
        var password = binding.pwInputEditText.toString()

    }
}