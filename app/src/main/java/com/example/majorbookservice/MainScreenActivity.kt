package com.example.majorbookservice

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majorbookservice.Data.DTO.MajorBooks
import com.example.majorbookservice.UI.adapter.Adapter
import com.example.majorbookservice.databinding.ActivityMainScreenBinding


class MainScreenActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainScreenBinding
    val mDatas=mutableListOf<MajorBooks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main_screen)
        setupAutoCompleteView()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                /** 검색버튼에 사용할 액션들 구현 */
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.textHint.visibility = View.GONE
                return true
            }

        })


        binding.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                softkeyboardHide()
                binding.searchView.onActionViewCollapsed()
                binding.textHint.visibility = View.VISIBLE
                return true
            }
        })

        //binding.searchView.setOnSearchClickListener(object: SearchView.On)
        binding.searchView.setOnSearchClickListener {
            binding.textHint.visibility = View.GONE
        }

        /** RecyclerView Test를 위해 사용할 데이터 셋.
         *
         * 추후 삭제 예정
         *
         * */
        initDogRecyclerView()
        initializelist()


        /** Spinner 아이템 상태에 따른 layout 변경 */
        binding.spinnerView.setOnClickListener {
            binding.spinnerView.show()
            binding.spinnerView.setBackgroundResource(R.drawable.spinner_main_background_opened)
            //binding.spinnerView.setPadding(12, 8, 0, 5)
            binding.spinnerView.setPaddingRelative(12, 1, 5, 0)
        }

        binding.spinnerView.apply {
            setOnSpinnerItemSelectedListener<String> { _, _, _, item ->
                binding.spinnerView.setBackgroundResource(R.drawable.spinner_main_background_closed)
                binding.spinnerView.setPaddingRelative(12, 1, 5, 0)
            }
        }

    }

    /** spinner 내부에 들어갈 데이터 추가 및 연결 */
    private fun setupAutoCompleteView() {
        val dataList=getDataList()
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,R.layout.spinner_item_mainscreen, dataList )

        /*binding.spinnerFilter.setAdapter(adapter)*/
       /* mContentViewBinding.spinnerFilter.onItemClickListener =
            AdapterView.OnItemClickListener { parent, arg1, position, id ->
                //TODO: You can your own logic.
                // onClick에 관련된 이벤트 추가로 구현 가능
            }*/
    }
    /** 키보드 내리기 */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }

    fun softkeyboardHide() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchView.windowToken, 0)
    }

    private fun getDataList(): ArrayList<String> {
        val dataList=ArrayList<String>()
        dataList.add("교수명")
        dataList.add("과목명")
        dataList.add("학과명")
        return dataList
    }

    /** 리사이클러뷰 화면 실행 */
    fun initDogRecyclerView(){
        val adapter= Adapter() //어댑터 객체 만듦
        adapter.datalist=mDatas //데이터 넣어줌
        binding.recyclerView.adapter=adapter //리사이클러뷰에 어댑터 연결
        binding.recyclerView.layoutManager= LinearLayoutManager(this) //레이아웃 매니저 연결
    }

    fun initializelist(){ //임의로 데이터 넣어서 만들어봄
        with(mDatas){
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

}