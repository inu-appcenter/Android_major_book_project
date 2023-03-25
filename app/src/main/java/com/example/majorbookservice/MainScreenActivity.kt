package com.example.majorbookservice

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.MajorBooks
import com.example.majorbookservice.Data.DTO.SubjectDto
import com.example.majorbookservice.Data.DTO.SubjectResponse
import com.example.majorbookservice.UI.adapter.Adapter
import com.example.majorbookservice.UI.adapter.ToDoAdapter
import com.example.majorbookservice.databinding.ActivityMainScreenBinding
import com.example.majorbookservice.databinding.ItemBookBinding
import com.metacoding.major_book.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable


class MainScreenActivity : AppCompatActivity(), ToDoAdapter.ItemClickListener {
    lateinit var binding: ActivityMainScreenBinding
    //private lateinit var myAdapter: ToDoAdapter
    val mDatas = mutableListOf<MajorBooks>()
    private var nameText = ""
    private var professorText = ""
    private var departmentText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen)
        setupAutoCompleteView()

        //1. retrofit 만들기
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book-service.inuappcenter.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //2. retrofit 서비스 등록하기
        val retrofitService = retrofit.create(RetrofitService::class.java)



        val searchList = mutableListOf<SubjectDto>()

        retrofitService.subjectInquiry(professorText,departmentText,nameText).enqueue(object : Callback<SubjectResponse> {

            override fun onResponse(
                call: Call<SubjectResponse>,
                response: Response<SubjectResponse>
            ) {
                //성공한 경우
                if (response.isSuccessful) {
                    val searchResponse = response.body()!!
                    Log.d("retrofit", searchResponse.toString())



                    //val todoList = response.body()!!
                    Log.d("retrofit", searchResponse.size.toString())

                    searchResponse.forEach{
                        //Log.d("retrofit",it.contents)
                        searchList.add(it)
                    }
                }

                if(searchList.size == 0){
                    binding.noResults.isVisible = true
                }else{
                    binding.noResults.isVisible = false
                }
                searchList.forEach {
                    //Log.d("retrofit",it.contents)
                }

                binding.recyclerView.layoutManager =
                    LinearLayoutManager(this@MainScreenActivity, RecyclerView.VERTICAL, false)
                binding.recyclerView.adapter = ToDoAdapter(ItemBookBinding.inflate(layoutInflater), searchList, this@MainScreenActivity)
            }

            override fun onFailure(call: Call<SubjectResponse>, t: Throwable) {

            }
        })

        binding.searchViewIcon.setOnClickListener {
            searchList.clear()
            nameText = ""
            professorText = ""
            departmentText = ""

            when(binding.spinnerView.text.toString()){
                "교수명" -> professorText = binding.searchView.text.toString()
                "과목명" -> nameText= binding.searchView.text.toString()
                "학과명" -> departmentText = binding.searchView.text.toString()
                else -> professorText = binding.searchView.text.toString()
            }

            if(binding.searchView.isFocused){
                binding.searchView.text.clear()
            }else{
                Toast.makeText(applicationContext,"검색중...", Toast.LENGTH_SHORT).show()
                retrofitService.subjectInquiry(professorText,departmentText,nameText).enqueue(object : Callback<SubjectResponse> {

                    override fun onResponse(
                        call: Call<SubjectResponse>,
                        response: Response<SubjectResponse>
                    ) {
                        //성공한 경우
                        if (response.isSuccessful) {
                            val searchResponse = response.body()!!
                            Toast.makeText(applicationContext,"검색 성공~!", Toast.LENGTH_SHORT).show()
                            Log.d("retrofit", searchResponse.toString())



                            //val todoList = response.body()!!
                            Log.d("retrofit", searchResponse.size.toString())

                            searchResponse.forEach{
                                //Log.d("retrofit",it.contents)
                                searchList.add(it)
                            }
                        }

                        if(searchList.size == 0){
                            binding.noResults.isVisible = true
                        }else{
                            binding.noResults.isVisible = false
                        }
                        searchList.forEach {
                            //Log.d("retrofit",it.contents)
                        }

                        binding.recyclerView.layoutManager =
                            LinearLayoutManager(this@MainScreenActivity, RecyclerView.VERTICAL, false)
                        binding.recyclerView.adapter = ToDoAdapter(ItemBookBinding.inflate(layoutInflater), searchList, this@MainScreenActivity)
                    }

                    override fun onFailure(call: Call<SubjectResponse>, t: Throwable) {

                    }
                })
            }
        }

        binding.searchView.setOnFocusChangeListener { v, hasFocus ->
            binding.searchView.setBackgroundResource(R.drawable.bg_gray1_5dp_line)
            if (binding.searchView.text.isNotEmpty()) {
                binding.searchViewIcon.isVisible = true
                binding.searchViewIcon.setImageResource(R.drawable.img)

            } else {
                binding.searchViewIcon.isVisible = false
                //binding.searchViewIcon.setImageResource(R.drawable.ic_search_figma)

            }
        }

//        binding.searchView.doAfterTextChanged {
//
//        }

        binding.searchView.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
//                binding.searchViewIcon.setImageResource(R.drawable.ic_search_figma)

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().trim().isNotEmpty()) {
                    binding.searchViewIcon.isVisible = true
                    binding.searchViewIcon.setImageResource(R.drawable.img)

                } else {
                    binding.searchViewIcon.isVisible = false
                    //binding.searchViewIcon.setImageResource(R.drawable.ic_search_figma)

                }
            }
        })


        /** RecyclerView Test를 위해 사용할 데이터 셋.
         *
         * 추후 삭제 예정
         *
         * */
        initRecyclerView()
        initializelist()


        /** Spinner 아이템 상태에 따른 layout 변경 */
        binding.spinnerView.setOnClickListener {
            binding.spinnerView.show()
            binding.spinnerView.setBackgroundResource(R.drawable.spinner_main_background_opened)
            //binding.spinnerView.setPadding(12, 8, 0, 5)
        }

        binding.spinnerView.apply {
            setOnSpinnerItemSelectedListener<String> { _, _, _, item ->
                binding.spinnerView.setBackgroundResource(R.drawable.spinner_main_background_closed)
            }
        }

    }

    /** spinner 내부에 들어갈 데이터 추가 및 연결 */
    private fun setupAutoCompleteView() {
        val dataList = getDataList()
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.spinner_item_mainscreen, dataList)

        /*binding.spinnerFilter.setAdapter(adapter)*/
        /* mContentViewBinding.spinnerFilter.onItemClickListener =
             AdapterView.OnItemClickListener { parent, arg1, position, id ->
                 //TODO: You can your own logic.
                 // onClick에 관련된 이벤트 추가로 구현 가능
             }*/
    }

    /** 키보드 내리기 */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }

    fun softkeyboardHide() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)
    }

    private fun getDataList(): ArrayList<String> {
        val dataList = ArrayList<String>()
        dataList.add("교수명")
        dataList.add("과목명")
        dataList.add("학과명")
        return dataList
    }

    /** 리사이클러뷰 화면 실행 */
    fun initRecyclerView() {
//        val adapter = Adapter() //어댑터 객체 만듦
//        adapter.datalist = mDatas //데이터 넣어줌
//        binding.recyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
//        binding.recyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
    }

    fun initializelist() { //임의로 데이터 넣어서 만들어봄
        with(mDatas) {
            add(MajorBooks("UI/UX", "Lee", "컴퓨터공학부"))
            add(MajorBooks("UI/UX", "Lee", "컴퓨터공학부"))
            add(MajorBooks("UI/UX", "Lee", "컴퓨터공학부"))
            add(MajorBooks("UI/UX", "Lee", "컴퓨터공학부"))
            add(MajorBooks("UI/UX", "Lee", "컴퓨터공학부"))
            add(MajorBooks("UI/UX", "Lee", "컴퓨터공학부"))
            add(MajorBooks("UI/UX", "Lee", "컴퓨터공학부"))
            add(MajorBooks("UI/UX", "Lee", "컴퓨터공학부"))
        }
    }

    //focus 해제
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action === MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                    binding.searchViewIcon.setImageResource(R.drawable.ic_search_figma)
                    binding.searchView.setBackgroundResource(R.drawable.bg_gray1_5dp)

                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onClick(id: Int) {   //클릭하면 id 값을 입력받는다.

        Log.d("retrofit","책 검색중입니다...")
        Log.d("retrofit",id.toString())

        val intent = Intent(this, MajorBookActivity::class.java)
        intent.putExtra("subject", id)

        startActivity(intent)

//        //1. retrofit 만들기
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://book-service.inuappcenter.kr/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        //2. retrofit 서비스 등록하기
//        val retrofitService = retrofit.create(RetrofitService::class.java)
//
//        val searchList = mutableListOf<SubjectBookResponse>()
//
//        retrofitService.subjectBookInquiry(id, id).enqueue(object : Callback<SubjectBookResponse> {
//
//            override fun onResponse(
//                call: Call<SubjectBookResponse>,
//                response: Response<SubjectBookResponse>
//            ) {
//                //성공한 경우
//                if (response.isSuccessful) {
//
//                    Log.d("retrofit","책 검색에 성공했습니다...")
//
//                    val searchResponse = response.body()!!
//                    //Toast.makeText(applicationContext,"책 검색 성공~!", Toast.LENGTH_SHORT).show()
//                    Log.d("retrofit", searchResponse.books)
//
//                }else{
//                    Log.d("retrofit", "실패했습니다")
//                }
//            }
//
//            override fun onFailure(call: Call<SubjectBookResponse>, t: Throwable) {
//
//            }
//        })
    }
}