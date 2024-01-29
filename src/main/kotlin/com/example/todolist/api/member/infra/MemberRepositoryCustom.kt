package com.example.todolist.api.member.infra

import com.example.todolist.api.member.domain.QMember.member
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository


@Repository
class MemberRepositoryCustom (private val jpaQueryFactory: JPAQueryFactory){

    fun existsByEmail(email: String): Boolean {
        return jpaQueryFactory.selectOne()
            .from(member)
            .where(member.email.eq(email))
            .fetchFirst() != null
    }

    fun existsByNickName(nickName: String): Boolean {
        return jpaQueryFactory.selectOne()
            .from(member)
            .where(member.nickName.eq(nickName))
            .fetchFirst() != null
    }
}