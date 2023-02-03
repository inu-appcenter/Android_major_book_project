package com.example.majorbookservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** test */
        val fragmentManager = supportFragmentManager

//        //붙였다 떼었다 할 fragment 객체 만들어 주기
//        val fragment = FragmentSplash()
//
//        //Transaction(작업의 단위) 만들어주기
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.rootView, fragment)
//        transaction.commit()

    }
}