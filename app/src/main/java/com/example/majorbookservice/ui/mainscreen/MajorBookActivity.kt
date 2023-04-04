package com.example.majorbookservice.ui.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majorbookservice.Data.DTO.Book
import com.example.majorbookservice.Data.DTO.MajorBooks
import com.example.majorbookservice.Data.DTO.Subject
import com.example.majorbookservice.R
import com.example.majorbookservice.databinding.ActivityMajorBookBinding
import com.example.majorbookservice.ui.adapter.MajorBookAdapter

class MajorBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMajorBookBinding
    val mDatas = mutableListOf<Subject>()
    val mDatas2 = mutableListOf<MajorBooks>()
    val bookData = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_major_book)

//        //툴바 타이틀 정해주기
//        setSupportActionBar(binding.topAppBar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//        binding.toolbar.title = "융합디자인론/한혜진"

        // val toolbar = findViewById(R.id.topAppBar) as androidx.appcompat.widget.Toolbar
        //setSupportActionBar(toolbar)    //toolbar를 갖고와 activity의 앱바로 설정
//        toolbar?.navigationIcon = ContextCompat.getDrawable(this,R.drawable.baseline_arrow_back_ios_new_24) //style이 지정된 drawable을 얻는 법

        /** toolbar binding 작업 */
        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)

        /** toolbar 뒤로가기 버튼 이벤트 구현 */
        toolbar?.setNavigationOnClickListener {
            val intent = Intent(this, MainScreenActivity::class.java)
            startActivity(intent)
        }


        val clickedId = intent.getIntExtra("clicked_id", 0)
        Log.d("clicked_item", clickedId.toString())


        /** 샘플 데이터 입력 완료 */
        val adapter= MajorBookAdapter()
        val viewModel = MajorBookModel()

        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)

        viewModel.getBooks(clickedId)
        viewModel.setList.observe(this, Observer {
            adapter.setBook(it.peekContent())
            adapter.notifyDataSetChanged()
        })



/*//    //액션버튼 메뉴 액션바에 집어 넣기
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
//    }*/

    }

}