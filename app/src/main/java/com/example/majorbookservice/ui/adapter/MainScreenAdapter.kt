package com.example.majorbookservice.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.Subject
import com.example.majorbookservice.databinding.ItemBookBinding
import com.example.majorbookservice.ui.mainscreen.MajorBookActivity

class MainScreenAdapter: RecyclerView.Adapter<MainScreenAdapter.MyViewHolder>() {

    var datalist = mutableListOf<Subject>()

    fun setSubject(input_list:ArrayList<Subject>) {
        datalist.addAll(input_list)
    }

    fun cleanSubject() {
        datalist.clear()
    }

    inner class MyViewHolder(private val binding: ItemBookBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Subject) {
            binding.lecture.text = data.subjectName
            binding.professor.text = data.professorName
            binding.subjectName.text = data.subjectName
            binding.type.text = data.subjectType

            itemView.setOnClickListener {
                Log.d("item_clicked", data.id.toString())

                /** 클릭된 값의 정보들을 보내준다. */
                val intent = Intent(itemView?.context, MajorBookActivity::class.java)
                intent.putExtra("clicked_id", data.id)
                intent.putExtra("professorName", data.professorName)
                intent.putExtra("subjectName", data.subjectName)
                intent.putExtra("subjectType", data.subjectType)
                intent.putExtra("departmentName", data.department)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
}