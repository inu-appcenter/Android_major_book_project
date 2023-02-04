package com.example.majorbookservice

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.majorbookservice.databinding.ActivityMainScreenBinding


class MainScreenActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main_screen)
        setupAutoCompleteView()
    }

    /** spinner 내부에 들어갈 데이터 추가 및 연결 */
    private fun setupAutoCompleteView() {
        val dataList=getDataList()
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,R.layout.spinner_item_mainscreen, dataList )
        binding.spinnerFilter.setAdapter(adapter)
       /* mContentViewBinding.spinnerFilter.onItemClickListener =
            AdapterView.OnItemClickListener { parent, arg1, position, id ->
                //TODO: You can your own logic.
                // onClick에 관련된 이벤트 추가로 구현 가능
            }*/

    }


    private fun getDataList(): ArrayList<String> {
        val dataList=ArrayList<String>()
        dataList.add("교수명")
        dataList.add("과목명")
        dataList.add("학과명")
        return dataList
    }
}