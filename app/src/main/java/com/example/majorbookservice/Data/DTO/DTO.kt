package com.example.majorbookservice.Data.DTO

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("author")
    var author: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("publisher")
    var publisher: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("year")
    var year: Int
)

data class Subject(
    @SerializedName("departmentName")
    var department: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("professorName")
    var professorName: String,
    @SerializedName("subjectName")
    var subjectName: String,
    @SerializedName("subjectType")
    var subjectType: String
)

data class Filter(
    var professorName: String,
    var departmentName: String,
    var subjectName: String
)

data class SubjectResponse(
    @SerializedName("books")
    var books: ArrayList<Book>,
    @SerializedName("departmentName")
    var department: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("professorName")
    var professorName: String,
    @SerializedName("subjectName")
    var subjectName: String,
    @SerializedName("subjectType")
    var subjectType: String
)



/*
data class SignInRequestDto(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)

data class SignInResponseDto(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("token")
    var token: String
)

data class SignUpRequestDto(
    @SerializedName("college")
    var college: String,
    @SerializedName("department")
    var department: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("password")
    var password: String
)

data class SignUpResponseDto(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("success")
    var success: Boolean
)*/

