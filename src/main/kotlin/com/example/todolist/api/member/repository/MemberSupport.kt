package com.example.todolist.api.member.repository

interface MemberSupport {
    fun existsByEmail(email: String): Boolean
    fun existsByNickName(nickName: String): Boolean
}