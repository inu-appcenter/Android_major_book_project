package com.example.majorbookservice

import com.example.majorbookservice.Data.DTO.SubjectResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


//API 만들기
interface RetrofitService {

    //@Headers("Content-Type: application/json")
    //@Headers("Accept-Encoding: identity")

    /*과목 검색*/
    @GET("/subjects")
    fun subjectInquiry(
        @Query("professor") professor: String,
        @Query("department") department: String,
        @Query("name") name: String
    ): Call<SubjectResponse>
}


