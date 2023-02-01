package com.example.majorbookservice

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.majorbookservice.databinding.FragmentLogInBinding

class FragmentSplash : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        // 일정 시간 지연 이후 실행하기 위한 코드
        Handler(Looper.getMainLooper()).postDelayed({

            // 일정 시간이 지나면 FragmentLogIn으로 이동
            val fragment= FragmentLogIn()

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.rootView, FragmentLogIn())
                .commit()

        }, 2000) // 시간 2.5초 이후 실행
    }
}