package com.example.majorbookservice.UI.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.SubjectDto
import com.example.majorbookservice.databinding.ItemBookBinding

//뷰 객체를 가지는 뷰 홀더
class ToDoViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

class ToDoAdapter(
    val binding: ItemBookBinding,
    val subject: MutableList<SubjectDto>,
    private val itemClickListener: ItemClickListener? = null,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //항목 개수 판단
    override fun getItemCount(): Int {
        Log.d("retrofit","아이템 수: " + subject.size.toString())

        return subject.size
    }

    //항목의 뷰를 갖는 뷰홀더를 준비하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ToDoViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    //뷰홀더의 뷰에 데이터 출력
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ToDoViewHolder).binding

        //뷰에 데이터 넣기
        binding.lecture.text = subject[position].name
        binding.professor.text = subject[position].professor
        binding.major.text = subject[position].department

        holder.itemView.setOnClickListener{
            Log.d("retrofit", "item root click: ${position}")
            Log.d("retrofit", "subject id: ${subject[position].id}")
            itemClickListener?.onClick(subject[position].id)
        }
    }

    //    //clickListener
    interface ItemClickListener {
        fun onClick(id: Int)
    }
}