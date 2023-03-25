package com.example.majorbookservice

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.SignUpRequestDto
import com.example.majorbookservice.Data.DTO.SubjectDto
import com.example.majorbookservice.Data.DTO.SubjectResponse
import com.example.majorbookservice.UI.adapter.ToDoAdapter
import com.example.majorbookservice.databinding.ActivityCreateAccountBinding
import com.example.majorbookservice.databinding.ItemBookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.LogManager

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    var school: String = ""
    var major: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //1. retrofit 만들기
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book-service.inuappcenter.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //2. retrofit 서비스 등록하기
        val retrofitService = retrofit.create(RetrofitService::class.java)

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


        var emailText = binding.editTextInputId.toString()
        var passwordText = binding.editTextInputPassword.toString()
        var nameText = binding.editTextInputName.toString()

        emailText = "test123@gmail.com"
        passwordText = "Password123!"
        nameText = "현승"

        /** 회원가입에 대한 부분 */



        /*binding.btnSignUp.setOnClickListener {
            retrofitService.postSignUp(inputData).enqueue(object :
                Callback<SignUpRequestDto> {

                override fun onResponse(
                    call: Call<SignUpRequestDto>,
                    response: Response<SignUpRequestDto>
                ) {
                    //성공한 경우
                    if (response.isSuccessful) {
                        Log.d("retrofit", "SUCCESS")
                    }
                }

                override fun onFailure(call: Call<SignUpRequestDto>, t: Throwable) {
                    Log.d("retrofit", t.toString())
                }
            })

            Log.d("Message", "message")
        }*/

        var inputData = SignUpRequestDto("인문대학", "국어국문학과", emailText, nameText, passwordText)

        binding.btnSignUp.setOnClickListener {
            // var testData = SignUpRequestDto("인문대학", "국어국문학과", "test11@naver.com", "lee", "Password!")
            var testData = SignUpRequestDto(
                school,
                major,
                binding.editTextInputId.toString(),
                binding.editTextInputName.toString(),
                binding.editTextInputPassword.toString()
            )

            Log.d("Clicked", "text is CLicked")

            retrofitService.postSignUp(testData).enqueue(object :
                Callback<SignUpRequestDto> {
                override fun onResponse(
                    call: Call<SignUpRequestDto>,
                    response: Response<SignUpRequestDto>
                ) {
                    //성공한 경우
                    if (response.isSuccessful) {
                        Log.d("retrofit", "SUCCESS")
                        Toast.makeText(this@CreateAccountActivity, "회원가입 성공", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<SignUpRequestDto>, t: Throwable) {
                    Log.d("retrofit", t.toString())

                }
            })

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
