package com.example.todolist.api.member.adapter

import com.example.todolist.api.member.domain.Member
import com.example.todolist.api.member.port.MemberPort
import com.example.todolist.api.member.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class MemberAdepter(private val memberRepository: MemberRepository) : MemberPort {

    override fun save(newMember: Member) {
        memberRepository.save(newMember)
    }

    override fun findById(memberId: Long): Member {
        return memberRepository.findById(memberId).orElseThrow {
            NoSuchElementException("Member is not found")
        }
    }

    override fun duplicateEmailCheck(email: String): Boolean {
        return memberRepository.existsByEmail(email)
    }

    override fun duplicateNickNameCheck(nickName: String): Boolean {
        return memberRepository.existsByNickName(nickName)
    }
}