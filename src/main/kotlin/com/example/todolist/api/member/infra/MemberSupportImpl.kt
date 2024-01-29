package com.example.todolist.api.member.infra

import com.example.todolist.api.member.domain.QMember.member
import com.example.todolist.api.member.repository.MemberSupport
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository


@Repository
class MemberSupportImpl (private val jpaQueryFactory: JPAQueryFactory) : MemberSupport {

    override fun existsByEmail(email: String): Boolean {
        return jpaQueryFactory.selectOne()
            .from(member)
            .where(member.email.eq(email))
            .fetchFirst() != null
    }

    override fun existsByNickName(nickName: String): Boolean {
        return jpaQueryFactory.selectOne()
            .from(member)
            .where(member.nickName.eq(nickName))
            .fetchFirst() != null
    }
}