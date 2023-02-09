package com.example.majorbookservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.majorbookservice.databinding.ActivityMajorBookBinding

class MajorBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMajorBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMajorBookBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_major_book)

//        //툴바 타이틀 정해주기
//        setSupportActionBar(binding.topAppBar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//        binding.toolbar.title = "융합디자인론/한혜진"

        val toolbar = findViewById(R.id.topAppBar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        toolbar?.navigationIcon = ContextCompat.getDrawable(this,R.drawable.baseline_arrow_back_ios_new_24)
        toolbar?.setNavigationOnClickListener {
            Toast.makeText(applicationContext,"Navigation icon was clicked",Toast.LENGTH_SHORT).show()
            val intent= Intent( this,MainScreenActivity::class.java)
            startActivity(intent)
        }
    }

//    //액션버튼 메뉴 액션바에 집어 넣기
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_menu, menu)
//        return true
//    }
//
//    //액션버튼 클릭 했을 때
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item?.itemId == R.id.topAppBar) {
//            //Toast.makeText(applicationContext, "이전 화면으로 이동합니다.", Toast.LENGTH_LONG).show()
//
//            finish()
//            return super.onOptionsItemSelected(item)
//        }else{
//            return super.onOptionsItemSelected(item)
//        }
//    }


}