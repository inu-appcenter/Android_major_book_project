package com.example.majorbookservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majorbookservice.Data.DTO.BookDto
import com.example.majorbookservice.Data.DTO.MajorBooks
import com.example.majorbookservice.UI.adapter.Adapter
import com.example.majorbookservice.UI.adapter.MajorBookAdapter
import com.example.majorbookservice.databinding.ActivityMajorBookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMajorBookBinding
    val mDatas = mutableListOf<BookDto>()
    val mDatas2 = mutableListOf<MajorBooks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMajorBookBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_book_list)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_major_book)

        /** 레트로핏 서비스 등록하기 */
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book-service.inuappcenter.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //2. retrofit 서비스 등록하기
        val retrofitService = retrofit.create(RetrofitService::class.java)

        val bookList = mutableListOf<BookDto>()
        val list = mutableListOf<MajorBooks>()


        initializelist2()
        initDogRecyclerView()


        var bookId = 1
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
                    adapter.datalist = mDatas
                    Log.d("dataset", mDatas.toString())

                    /* binding.recyclerView.adapter = adapter
                     binding.recyclerView.layoutManager =
                         LinearLayoutManager(this@MajorBookActivity, RecyclerView.VERTICAL, false)*/

                    binding.recyclerView.setHasFixedSize(true)
                    // 데이터 셋은 잘 들어가 있음.
                    var test = bookList[0].title
                    Log.d("retrofit2", "$test")



                }
            }

            override fun onFailure(call: Call<BookDto>, t: Throwable) {
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
        binding.recyclerView.layoutManager = LinearLayoutManager(this@BookListActivity) //레이아웃 매니저 연결
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