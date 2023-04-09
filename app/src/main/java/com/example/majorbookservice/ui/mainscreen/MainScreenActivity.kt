package com.example.majorbookservice.ui.mainscreen

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.*
import com.example.majorbookservice.R
import com.example.majorbookservice.databinding.ActivityMainScreenBinding
import com.example.majorbookservice.databinding.ItemBookBinding
import com.example.majorbookservice.ui.adapter.MainScreenAdapter
import com.skydoves.powerspinner.PowerSpinnerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding
    var itemValue: String = ""
    lateinit var filter:Filter
    var inputString:String = ""

    private var nameText = ""
    private var professorText = ""
    private var departmentText = ""
    private var test = ""

    private val adapter= MainScreenAdapter()
    private val viewModel = MainScreenModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen)

        // 스피너 설정
        initSpinner()

        /** 어댑터 연결*/
        filter = Filter("","","")

        /** 검색 버튼 클릭 이벤트*/
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
        // binding.searchView.setOnQueryTextListener(searchViewTextListner)

        /** 하은이의 검색버튼 클릭 이벤트 */

        initSearchView()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결

        /*viewModel.getSubjects(filter)
        viewModel.setList.observe(this, Observer {
            Log.d("search", "제일 바깥에서도 데이터 추가")
            adapter.setSubject(it.peekContent())
            adapter.notifyDataSetChanged()
        })*/


    }

    private fun getSubject(filter: Filter) {
        adapter.cleanSubject()
        adapter.datalist.clear()
        viewModel.getSubjects(filter)
        viewModel.setList.observe(this, Observer {
            adapter.setSubject(it.peekContent())
            adapter.notifyDataSetChanged()
            Log.d("search", it.peekContent().toString())
        })
        adapter.cleanSubject()
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

    private fun initSearchView() {
        binding.eraseIcon.setOnClickListener {
            binding.searchView.text.clear()
        }

        binding.searchViewIcon.setOnClickListener {
            adapter.cleanSubject()
            // 키패드 내리기
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)

            when(binding.spinnerView.text.toString()){
                "교수명" -> professorText = binding.searchView.text.toString()
                "과목명" -> nameText= binding.searchView.text.toString()
                "학과명" -> departmentText = binding.searchView.text.toString()
                else -> professorText = binding.searchView.text.toString()
            }

            filter = Filter(professorText, departmentText,nameText)

            Log.d("search_searchViewIcon", filter.toString())

            nameText = ""
            professorText = ""
            departmentText = ""

            getSubject(filter)
            adapter.cleanSubject()
        }

        /** searchView 클릭 시 테두리 색상 변경 */
        binding.searchView.setOnFocusChangeListener { v, hasFocus ->
            adapter.cleanSubject()
            binding.searchView.setBackgroundResource(R.drawable.bg_gray1_5dp_line)
            if (binding.searchView.text.isNotEmpty()) { }
        }


        binding.searchView.setOnKeyListener { v, keyCode, event ->
            adapter.cleanSubject()
            if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                adapter.cleanSubject()

                val textString: String = binding.searchView.getText().toString()
                binding.searchView.setText(textString.replace("\n",""))
                binding.searchView.setSelection(
                    binding.searchView.getText().length
                )

                // 키패드 내리기
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)

                when(binding.spinnerView.text.toString()){
                    "교수명" -> professorText = binding.searchView.text.toString()
                    "과목명" -> nameText= binding.searchView.text.toString()
                    "학과명" -> departmentText = binding.searchView.text.toString()
                    else -> professorText = binding.searchView.text.toString()
                }

                filter = Filter(professorText, departmentText,nameText)

                Log.d("search_searchView", filter.toString())
                nameText = ""
                professorText = ""
                departmentText = ""

                getSubject(filter)
            }
            return@setOnKeyListener false
        }

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.eraseIcon.isVisible = s.toString().trim().isNotEmpty()
            }
        })
    }
}