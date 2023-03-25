package com.example.majorbookservice.Data.DTO

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("author")
    val author: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isSaved")
    val isSaved: Boolean,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: Int
)

data class SubjectDto(
    @SerializedName("department")
    val department: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("professor")
    val professor: String,
    @SerializedName("subjectType")
    val subjectType: String
)

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
)