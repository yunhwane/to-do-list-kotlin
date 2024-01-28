package com.example.todolist.api.member.service

data class MemberRequest(
        val email: String,
        val password: String,
        val nickName: String
)
