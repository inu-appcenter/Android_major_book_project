package com.example.majorbookservice.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.Book
import com.example.majorbookservice.Data.DTO.Subject
import com.example.majorbookservice.databinding.ItemMajorBookBinding
import com.example.majorbookservice.databinding.ItemSavedBookBinding

class MajorBookAdapter(
    //private val bookList: ArrayList<Book>
) : RecyclerView.Adapter<MajorBookAdapter.MyViewHolder>() {

    var datalist = mutableListOf<Book>()
    lateinit var testlist: Book

    fun setBook(input_list:Book) {
        //datalist.addAll(input_list)
        testlist = input_list
        datalist.add(input_list)
    }

    inner class MyViewHolder(
        private val binding: ItemSavedBookBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Book) {
            /** firstLayout에 대한 정보들 */
            binding.bookNameTextView.text = data.title
            binding.bookAuthorTextView.text = data.author

            /** detalView에 대한 정보들 */
            binding.titleTextView.text = data.title
            binding.authorNameTextView.text = data.author
            binding.publisherNameTextView.text = data.publisher
            binding.yearOfPublicationTextView.text = data.year.toString()
            binding.typeTextView.text = data.type
            
            /** 이미지, 청구기호, 소장 위치, 대출가능 여부는 제외되었습니다. */

            itemView.setOnClickListener {
                // 간단하게 해당 위치에 해당하는 visible을 컨트롤할 수 있다.
                // 해당 위치가 몇번째 인지도 알 수 있다.
                Log.d("clicked", data.id.toString())


                // 마지막 상태를 기억하게 만들고 만약 오픈되어 있으면 닫아주고
                // 반대로 닫혀 있으면 열어준다.
                var status = binding.firstLayout.isVisible
                if(status) {
                    binding.detailView.isVisible = true
                    binding.firstLayout.isVisible = false
                }
                else {
                    binding.detailView.isVisible = false
                    binding.firstLayout.isVisible = true
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemSavedBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
}
