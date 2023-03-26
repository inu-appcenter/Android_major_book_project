package com.example.majorbookservice.UI.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.BookDto
import com.example.majorbookservice.Data.DTO.MajorBooks
import com.example.majorbookservice.Data.DTO.SubjectDto
import com.example.majorbookservice.databinding.ItemBookBinding
import com.example.majorbookservice.databinding.ItemMajorBookBinding
import com.example.majorbookservice.databinding.ItemSavedBookBinding

class MajorBookAdapter : RecyclerView.Adapter<MajorBookAdapter.MyViewHolder>() {

    var datalist = mutableListOf<BookDto>()

    inner class MyViewHolder(
        private val binding: ItemMajorBookBinding):
        RecyclerView.ViewHolder(binding.root) {


        fun bind(data: BookDto) {
            binding.titleTextView.text = data.title
            binding.publisherNameTextView.text = data.publisher
            binding.authorNameTextView.text = data.author
            binding.yearOfPublicationTextView.text = data.year.toString()

            binding
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MajorBookAdapter.MyViewHolder {
        val binding = ItemMajorBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
}
