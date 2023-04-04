package com.example.majorbookservice.ui.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.majorbookservice.Data.DTO.Book
import com.example.majorbookservice.Data.DTO.Filter
import com.example.majorbookservice.Data.DTO.Subject
import com.example.majorbookservice.Data.Event
import com.example.majorbookservice.Data.Repository

class MajorBookModel: androidx.lifecycle.ViewModel() {
    private var _setList = MutableLiveData<Event<Book>>()
    val setList: LiveData<Event<Book>> = _setList

    fun getBooks(bookId: Int) {
        Repository().getBooks(bookId,
            object: Repository.GetDataCallback<Book> {
                override fun onSuccess(data: Book?) {
                    if (data!=null) {
                        _setList.postValue(Event(data))
                        Log.d("retrofit_Model", "success")
                        Log.d("retrofit_Model", data.toString())
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("retrofit_Model", "failed")
                    Log.d("retrofit_Model", throwable.toString())
                }
            })
    }
}