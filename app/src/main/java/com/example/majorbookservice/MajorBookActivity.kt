package com.example.majorbookservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.BookDto
import com.example.majorbookservice.Data.DTO.MajorBooks
import com.example.majorbookservice.Data.DTO.SubjectDto
import com.example.majorbookservice.UI.adapter.Adapter
import com.example.majorbookservice.UI.adapter.MajorBookAdapter
import com.example.majorbookservice.UI.adapter.ToDoAdapter
import com.example.majorbookservice.databinding.ActivityMajorBookBinding
import com.example.majorbookservice.databinding.ItemBookBinding
import com.example.majorbookservice.databinding.ItemMajorBookBinding
import kotlinx.coroutines.handleCoroutineException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MajorBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMajorBookBinding
    val mDatas = mutableListOf<BookDto>()
    val mDatas2 = mutableListOf<MajorBooks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_major_book)

//        //툴바 타이틀 정해주기
//        setSupportActionBar(binding.topAppBar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//        binding.toolbar.title = "융합디자인론/한혜진"

        val toolbar = findViewById(R.id.topAppBar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)    //toolbar를 갖고와 activity의 앱바로 설정
//        toolbar?.navigationIcon = ContextCompat.getDrawable(this,R.drawable.baseline_arrow_back_ios_new_24) //style이 지정된 drawable을 얻는 법

        /**back arrow click event*/
        toolbar?.setNavigationOnClickListener {
            val intent= Intent( this,MainScreenActivity::class.java)
            startActivity(intent)
        }



        /** 레트로핏 서비스 등록하기 */
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book-service.inuappcenter.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //2. retrofit 서비스 등록하기
        val retrofitService = retrofit.create(RetrofitService::class.java)

        /** 데이터 받아옴*/
        val bookId = intent.getIntExtra("subject", -1)


        val bookList = mutableListOf<BookDto>()
        val list = mutableListOf<MajorBooks>()


        retrofitService.getBook(bookId).enqueue(object : Callback<BookDto> {
            override fun onResponse(call: Call<BookDto>, response: Response<BookDto>) {
                if(response.isSuccessful) {
                    val searchResponse = response.body()!!
                    Log.d("retrofit2", searchResponse.toString())

                    bookList.add(searchResponse)

                    /*binding.recyclerView.layoutManager =
                        LinearLayoutManager(this@MajorBookActivity, RecyclerView.VERTICAL, false)
                    binding.recyclerView.adapter =
                    MajorBookAdapter(ItemMajorBookBinding.inflate(layoutInflater), bookList) */

                    initializelist()

                    val adapter = MajorBookAdapter()
                    adapter.datalist = bookList
                    Log.d("dataset", mDatas.toString())

                    binding.recyclerView.adapter = adapter
                    binding.recyclerView.layoutManager =
                        LinearLayoutManager(this@MajorBookActivity, RecyclerView.VERTICAL, false)




                    binding.recyclerView.setHasFixedSize(true)
                    // 데이터 셋은 잘 들어가 있음.
                    var test = bookList[0].title
                    Log.d("retrofit2", "$test")

                    /*initializelist2()
                    initDogRecyclerView()*/

                }
            }

            override fun onFailure(call: Call<BookDto>, t: Throwable) {
                Log.d("retrofit2_error", "error")
            }
        })


        // 교수 이름 불러와야댐
        retrofitService.getSubject(bookId).enqueue(object : Callback<SubjectDto> {
            override fun onResponse(call: Call<SubjectDto>, response: Response<SubjectDto>) {
                if(response.isSuccessful) {
                    val searchResponse2 = response.body()!!
                    Log.d("retrofit2", searchResponse2.toString())


                    binding.name.text = searchResponse2.name
                    binding.subject.text = searchResponse2.subjectType
                    binding.major.text = searchResponse2.department

                }
            }

            override fun onFailure(call: Call<SubjectDto>, t: Throwable) {
                Log.d("retrofit2_error", "error")
            }
        })



    }
    fun initializelist() { //임의로 데이터 넣어서 만들어봄
        with(mDatas) {
            add(BookDto("test", 1, false, "test", "test", "test", 3))
            add(BookDto("test", 1, false, "test", "test", "test", 3))
            add(BookDto("test", 1, false, "test", "test", "test", 3))
            add(BookDto("test", 1, false, "test", "test", "test", 3))
            add(BookDto("test", 1, false, "test", "test", "test", 3))
            add(BookDto("test", 1, false, "test", "test", "test", 3))
            add(BookDto("test", 1, false, "test", "test", "test", 3))
            add(BookDto("test", 1, false, "test", "test", "test", 3))
            add(BookDto("test", 1, false, "test", "test", "test", 3))

        }
    }

    fun initDogRecyclerView() {
        val adapter = Adapter() //어댑터 객체 만듦
        adapter.datalist = mDatas2 //데이터 넣어줌
        binding.recyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
    }

    fun initializelist2() { //임의로 데이터 넣어서 만들어봄
        with(mDatas2) {
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