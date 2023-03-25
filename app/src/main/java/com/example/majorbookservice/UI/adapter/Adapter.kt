package com.example.majorbookservice.UI.adapter

import android.content.ClipData
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.MajorBooks
import com.example.majorbookservice.databinding.ItemBookBinding

class Adapter : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    var datalist = mutableListOf<MajorBooks>()

    inner class MyViewHolder(
        private val binding: ItemBookBinding):
        RecyclerView.ViewHolder(binding.root) {

        /** 데이터 값을 recyclerview에 bind 해준다. */
        fun bind(data: MajorBooks) {
            binding.lecture.text = data.className
            binding.major.text = data.classMajor
            binding.professor.text = data.professorName
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter.MyViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
}