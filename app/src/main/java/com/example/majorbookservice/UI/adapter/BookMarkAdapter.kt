package com.metacoding.major_book

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.majorbookservice.Data.DTO.Book
import com.example.majorbookservice.databinding.ItemSavedBookBinding

//뷰 객체를 가지는 뷰 홀더
class MyAdapter(
    val list: MutableList<Book>,
    private val itemClickListener: MyAdapter.ItemClickListener? = null,
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    //뷰홀더를 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //뷰를 그리기
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemSavedBookBinding.inflate(inflater, parent, false)

        //뷰 홀더에 뷰를 넣고 리턴
        return MyViewHolder(binding)
    }

    //뷰홀더에 데이터를 넣어주기
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //홀더에서 아이템뷰를 갖고 온 뒤
        val book = list[position]   //데이터 갖고 오기
        holder.bind(book)           //뷰홀더와 데이터 바인딩해주기 (넣어주기)


        holder.itemView.setOnClickListener {
            itemClickListener?.onClick(holder.itemView)
        }
    }

    //어댑터가 갖고있는 리스트의 데이터 개수를 반환
    override fun getItemCount(): Int {
        return list.size
    }

    //view holder는 inner class로 만듦
    class MyViewHolder(private val binding: ItemSavedBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //데이터를 연결해주는 함수
        fun bind(book: Book) {
            binding.apply {
                //list에서 데이터 갖고오기
                bookNameTextView.text = book.title
                bookAuthorTextView.text = book.author

                titleTextView.text = book.title
                authorNameTextView.text = book.author
                publisherNameTextView.text = book.publisher
                yearOfPublicationTextView.text = book.year.toString()
                typeTextView.text = book.type
                infoUrlTextView.text = book.info


            }
        }
    }

    //clickListener
    interface ItemClickListener {
        fun onClick(book: View)
    }
}
