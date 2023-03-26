package com.example.majorbookservice

import com.example.majorbookservice.Data.DTO.*
import retrofit2.Call
import retrofit2.http.*


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

    @GET("/books/{bookId}")
    fun getBook(
        @Path("bookId") bookId: Int
    ): Call<BookDto>

    @GET("/subject/{subjectId}")
    fun getSubject(
        @Path("subjectId") subjectId: Int
    ): Call<SubjectDto>

    @POST("/members/sign-in")
    fun postSignIn(
        @Body signIn: SignInRequestDto
    ): Call<SignInRequestDto>

    @POST("/members/sign-up")
    fun postSignUp(
        @Body signUp: SignUpRequestDto
    ): Call<SignUpRequestDto>
}


