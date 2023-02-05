package com.example.majorbookservice

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.majorbookservice.databinding.ActivityMainScreenBinding


class MainScreenActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main_screen)
        setupAutoCompleteView()

       /* binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.textView.text = "click x"

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })*/


        binding.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                softkeyboardHide()
                binding.searchView.onActionViewCollapsed()
                binding.textHint.visibility = View.VISIBLE
                //binding.textView.text = "click O"
                return true
            }
        })

        //binding.searchView.setOnSearchClickListener(object: SearchView.On)
        binding.searchView.setOnSearchClickListener {
            binding.textHint.visibility = View.GONE
        }
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
}