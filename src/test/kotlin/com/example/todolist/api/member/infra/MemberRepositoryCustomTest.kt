package com.example.todolist.api.member.infra

import com.example.todolist.UserType
import com.example.todolist.api.member.domain.Member
import com.example.todolist.api.member.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@Transactional
@DisplayName("MemberRepositoryCustom 테스트")
class MemberRepositoryCustomTest(@Autowired private val memberRepositoryCustom: MemberRepositoryCustom, @Autowired private val memberRepository: MemberRepository){

    private lateinit var member: Member

    @BeforeEach
    fun setUp(){
        member = Member(null, "wjsdbsghks96@naver.com","윤환이","1234", UserType.USER)
        memberRepository.save(member)
    }


    @Test
    fun `이메일 중복 체크 - 중복되는 경우`() {
        // given
        val email = "wjsdbsghks96@naver.com"

        // when
        val existsByEmail = memberRepositoryCustom.existsByEmail(email)

        // then
        assertThat(existsByEmail).isTrue()
    }

    @Test
    fun `이메일 중복 체크 - 중복되지 않는 경우`() {
        // given
        val email = "unique@example.com"

        // when
        val existsByEmail = memberRepositoryCustom.existsByEmail(email)

        // then
        assertThat(existsByEmail).isFalse()
    }

    @Test
    fun `닉네임 중복 체크 - 중복되는 경우`() {
        // given
        val nickName = "윤환이"

        // when
        val existsByNickName = memberRepositoryCustom.existsByNickName(nickName)

        // then
        assertThat(existsByNickName).isTrue()
    }

    @Test
    fun `닉네임 중복 체크 - 중복되지 않는 경우`() {
        // given
        val nickName = "윤환이2"

        // when
        val existsByNickName = memberRepositoryCustom.existsByNickName(nickName)

        // then
        assertThat(existsByNickName).isFalse()
    }

}