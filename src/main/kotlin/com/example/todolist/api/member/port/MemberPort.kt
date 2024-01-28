package com.example.todolist.api.member.port

import com.example.todolist.api.member.domain.Member
import org.springframework.stereotype.Component


@Component
interface MemberPort {
    fun save(newMember: Member)
    fun findById(memberId: Long): Member
    fun duplicateEmailCheck(email: String): Boolean
}