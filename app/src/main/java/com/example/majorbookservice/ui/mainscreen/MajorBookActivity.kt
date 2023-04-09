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

        val clickedId = intent.getIntExtra("clicked_id", 0)
        val professorName = intent.getStringExtra("professorName")
        val department = intent.getStringExtra("departmentName")
        val subjectType = intent.getStringExtra("subjectType")
        val subjectName = intent.getStringExtra("subjectName")

        /** toolbar binding 작업 */
        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        binding.topAppBar.title = "$subjectName/$professorName"
        binding.department.text = department
        binding.subjectType.text = subjectType
        binding.subject.text = subjectName
        binding.professorName.text = professorName

        /** toolbar 뒤로가기 버튼 이벤트 구현 */
        toolbar?.setNavigationOnClickListener {
            val intent = Intent(this, MainScreenActivity::class.java)
            startActivity(intent)
        }

        Log.d("clicked_item", clickedId.toString())

        /** adapter와 recyclerView 연결 */
        val adapter= MajorBookAdapter()
        val viewModel = MajorBookModel()

        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)

        viewModel.getBooks(clickedId)
        viewModel.setList.observe(this, Observer {
            adapter.setBook(it.peekContent())
            adapter.notifyDataSetChanged()
        })
    }
}