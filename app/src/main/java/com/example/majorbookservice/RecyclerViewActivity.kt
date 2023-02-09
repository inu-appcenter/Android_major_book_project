package com.example.majorbookservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majorbookservice.Data.DTO.Book
import com.example.majorbookservice.databinding.ActivityRecyclerViewBinding
import com.metacoding.major_book.MyAdapter

class RecyclerViewActivity : AppCompatActivity(), MyAdapter.ItemClickListener {

    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var myAdapter: MyAdapter
    private var selectedBook: Book? = null
    var books = mutableListOf<Book>()
    var latestView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

//        //실행중에 앱 바 제목 변경하기
//        //appbar(Toolbar)
//        val toolbarBodyTemplate = binding.toolbar
//        setSupportActionBar(toolbarBodyTemplate)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화 (화살표)
//        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
//        toolbarBodyTemplate.title = "신체 템플릿"
//
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var books = mutableListOf<Book>()

        //데이터 추가하기
        books.add(Book("do it! 깡쌤의 안드로이드 앱 개발 with 코틀린", "강성윤", "이지스퍼블리싱", 2021, "자료형태", "소장정보"))
        books.add(Book("Node.js 교과서", "조현영", "이지스퍼블리싱", 2021, "자료형태", "소장정보"))
        books.add(Book("명품 html5+css+javascript", "황기태", "이지스퍼블리싱", 2021, "자료형태", "소장정보"))
        books.add(Book("명품 html5+css+javascript", "황기태", "이지스퍼블리싱", 2021, "자료형태", "소장정보"))

        /**리사이클러뷰*/
        //1. Adapter 만들기
        //어댑터에 데이터 넣기

        myAdapter = MyAdapter(books, this)
        binding.recyclerView.apply {
            adapter = myAdapter

            //layout 매니저 지정하기
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

            //아이템 사이에 선 긋기
            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    R.drawable.bg_line_divider,
                    0,
                    0
                )
            )

        }
    }

    override fun onClick(book: View) {

        //선택된 단어 담기
//        selectedBook = book
        //books.remove(book)

        if(latestView == book){
            latestView?.findViewById<ConstraintLayout>(R.id.detailView)?.isVisible = false
            latestView?.findViewById<ConstraintLayout>(R.id.firstLayout)?.isVisible = true

            latestView = null
            return
        }


        //이전 창 닫기
        latestView?.findViewById<ConstraintLayout>(R.id.detailView)?.isVisible = false
        latestView?.findViewById<ConstraintLayout>(R.id.firstLayout)?.isVisible = true

        //새로운 창 열기
        val layout = book.findViewById<ConstraintLayout>(R.id.detailView)
        book.findViewById<ConstraintLayout>(R.id.firstLayout).isVisible = false
        layout.isVisible = true

        //열린 view 기억하기
        latestView = book
    }
}




