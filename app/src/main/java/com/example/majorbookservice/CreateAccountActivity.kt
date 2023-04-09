package com.example.majorbookservice

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.majorbookservice.Data.RetrofitService
import com.example.majorbookservice.databinding.ActivityCreateAccountBinding
import com.example.majorbookservice.ui.login.LogInActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    var school: String = ""
    var major: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /** 단과대 Spinner 부분 아이템 리스트 열렸을 때 테두리 값 변경*/
        binding.spinnerView.setOnClickListener {
            binding.spinnerView.show()
            binding.spinnerView.setBackgroundResource(R.drawable.spinner_main_background_opened)
            binding.spinnerView.setPaddingRelative(12, 0, 15, 0)
        }

        binding.spinnerView.apply {
            setOnSpinnerItemSelectedListener<String> { _, _, _, item ->
                binding.spinnerView.setBackgroundResource(R.drawable.spinner_main_background_closed)
                binding.spinnerView.setPaddingRelative(12, 0, 15, 0)
                school = item
                Log.d("school", school)
            }
        }

        /** 학과 Spinner 부분 아이템 리스트 열렸을 때 테두리 값 변경*/
        binding.spinnerView2.setOnClickListener {
            binding.spinnerView2.show()
            binding.spinnerView2.setBackgroundResource(R.drawable.spinner_main_background_opened)
            binding.spinnerView2.setPaddingRelative(12, 0, 15, 0)
            binding.spinnerView2.arrowTint = Color.parseColor("#AEAEAE")
        }

        binding.spinnerView2.apply {
            setOnSpinnerItemSelectedListener<String> { _, _, _, item ->
                binding.spinnerView2.setBackgroundResource(R.drawable.spinner_main_background_closed)
                binding.spinnerView2.setPaddingRelative(12, 0, 15, 0)
                major = item
                Log.d("major", major)
            }
        }



        //toolbar
        val toolbar = findViewById(R.id.topAppBar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        toolbar?.navigationIcon =
            ContextCompat.getDrawable(this, R.drawable.baseline_arrow_back_ios_new_24)
        toolbar?.setNavigationOnClickListener {
            Toast.makeText(applicationContext, "Navigation icon was clicked", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }
}
