package com.example.majorbookservice.ui.mainscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majorbookservice.Data.DTO.*
import com.example.majorbookservice.R
import com.example.majorbookservice.databinding.ActivityMainScreenBinding
import com.example.majorbookservice.ui.adapter.MainScreenAdapter
import com.skydoves.powerspinner.PowerSpinnerView


class MainScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding
    var itemValue: String = ""
    lateinit var filter:Filter
    var inputString:String = ""

    private var nameText = ""
    private var professorText = ""
    private var departmentText = ""
    private var test = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen)

        // 스피너 설정
        initSpinner()

        /** 어댑터 연결*/
        filter = Filter("","","")

        var searchViewTextListner: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    inputString = query
                    Log.d("search_click_query", query)

                    when(binding.spinnerView.text.toString()) {
                        "교수명" -> professorText = inputString
                        "과목명" -> nameText= inputString
                        "학과명" -> departmentText = inputString
                        else -> professorText = inputString
                    }

                    filter = Filter(professorText, departmentText,nameText)
                    Log.d("search_click", filter.toString())

                    nameText=""
                    professorText=""
                    departmentText=""

                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    Log.d("search_change", newText)
                    return true
                }
            }

        binding.searchView.setOnQueryTextListener(searchViewTextListner)


        val adapter= MainScreenAdapter()
        val viewModel = MainScreenModel()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결

        viewModel.getSubjects(filter)
        viewModel.setList.observe(this, Observer {
            adapter.setSubject(it.peekContent())
            adapter.notifyDataSetChanged()
        })


    }

    private fun initSpinner() {
        /** 데이터 추가 및 adapter 연결 */
        val dataList: ArrayList<String> = arrayListOf("교수명", "과목명", "학과명")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.spinner_item_mainscreen, dataList)

        /*binding.spinnerFilter.setAdapter(adapter)
         mContentViewBinding.spinnerFilter.onItemClickListener =
             AdapterView.OnItemClickListener { parent, arg1, position, id ->
                 //TODO: You can your own logic.
                 // onClick에 관련된 이벤트 추가로 구현 가능
             }*/

        /** Spinner 아이템 상태에 따른 layout 변경 */
        binding.spinnerView.setOnClickListener {
            binding.spinnerView.show()
            binding.spinnerView.setBackgroundResource(R.drawable.spinner_main_background_opened)
            //binding.spinnerView.setPadding(12, 8, 0, 5)
        }

        binding.spinnerView.apply {
            setOnSpinnerItemSelectedListener<String> { _, _, _, item ->
                binding.spinnerView.setBackgroundResource(R.drawable.spinner_main_background_closed)

                itemValue = item
                Log.d("item", itemValue)

                test = itemValue
            }
        }
    }
}