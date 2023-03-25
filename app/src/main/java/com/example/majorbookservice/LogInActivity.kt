package com.example.majorbookservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.majorbookservice.databinding.ActivityLogInBinding
import com.example.majorbookservice.databinding.ActivityMainBinding
import com.example.majorbookservice.databinding.ActivityMainScreenBinding
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

        //1. retrofit 만들기
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book-service.inuappcenter.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //2. retrofit 서비스 등록하기
        val retrofitService = retrofit.create(RetrofitService::class.java)

        var id = binding.idInputEditText.toString()
        var password = binding.pwInputEditText.toString()



    }
}