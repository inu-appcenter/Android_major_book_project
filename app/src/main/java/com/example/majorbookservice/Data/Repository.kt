package com.example.majorbookservice.Data

import android.util.Log
import com.example.majorbookservice.Data.DTO.Book
import com.example.majorbookservice.Data.DTO.Subject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Repository {
    private var service = RetrofitManager.retrofit.create(RetrofitService::class.java)

    fun getSubjects(professorName:String,
                    departmentName: String,
                    subjectName: String,
                    param: GetDataCallback<ArrayList<Subject>>) {
        val call = RetrofitManager.service.getSubject(professorName,departmentName, subjectName)
        call.enqueue(object: Callback<ArrayList<Subject>> {
            override fun onResponse(call: Call<ArrayList<Subject>>, response: Response<ArrayList<Subject>>) {
                Log.d("retrofit_Repository", "Repository.getSubjects")
                Log.d("retrofit_Repository", response.body().toString())
                response.body()?.let{param.onSuccess(it)}
            }

            override fun onFailure(call: Call<ArrayList<Subject>>, t: Throwable) {
                Log.d("retrofit", "Repository_failed")
                Log.d("retrofit", t.toString())
            }
        })
        Log.d("retrofit", "Repository")
    }

    fun getBooks(bookId:Int, param:GetDataCallback<Book>) {
        val call = RetrofitManager.service.getBook(bookId)
        call.enqueue(object: Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                response.body()?.let{param.onSuccess(it)}
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                Log.d("retrofit_Repository", t.toString())
            }
        })
    }

    interface GetDataCallback<T> {
        fun onSuccess(data: T?)
        fun onFailure(throwable: Throwable)
    }

    //fun getBook(param: GetDataCallback<>)

}