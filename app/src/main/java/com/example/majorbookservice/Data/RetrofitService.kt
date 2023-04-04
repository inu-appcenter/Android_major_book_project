package com.example.majorbookservice.Data

import com.example.majorbookservice.*
import com.example.majorbookservice.Data.DTO.*
import retrofit2.Call
import retrofit2.http.*


//API 만들기
interface RetrofitService {

    //@Headers("Content-Type: application/json")
    //@Headers("Accept-Encoding: identity")

    /*과목 검색*/
    @GET("/subjects")
    fun getSubject(
        @Query("professorName") professor: String,
        @Query("departmentName") department: String,
        @Query("subjectName") name: String
    ): Call<ArrayList<Subject>>

    @GET("/books/{bookId}")
    fun getBook(
        @Path("bookId") bookId: Int
    ): Call<Book>

    @GET("/subject/{subjectId}")
    fun getSubject2(
        @Path("subjectId") subjectId: Int
    ): Call<Subject>

   /* @POST("/members/sign-in")
    fun postSignIn(
        @Body signIn: SignInRequestDto
    ): Call<SignInRequestDto>

    @POST("/members/sign-up")
    fun postSignUp(
        @Body signUp: SignUpRequestDto
    ): Call<SignUpRequestDto>*/
}


