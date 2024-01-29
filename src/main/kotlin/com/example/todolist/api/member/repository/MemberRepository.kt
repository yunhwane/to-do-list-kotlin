package com.example.todolist.api.member.repository

import com.example.todolist.api.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberRepository :JpaRepository<Member,Long> ,MemberSupport{
}