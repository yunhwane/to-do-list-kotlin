package com.example.todolist.api.member.adapter

import com.example.todolist.UserType
import com.example.todolist.api.member.domain.Member
import com.example.todolist.api.member.port.MemberPort
import com.example.todolist.api.member.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@SpringJUnitConfig
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MemberAdepterTest @Autowired constructor(private val memberPort: MemberPort){

    private lateinit var member: Member

    @BeforeEach
    fun setUp(){
        member = Member(null, "wjsdbsghks96@naver.com","윤환이","1234", UserType.USER)
    }

    @DisplayName("유저 save 테스트")
    @Test fun `유저 DB 저장 테스트`(){

        // given
        memberPort.save(member)
        //when
        val storedMember = memberPort.findById(member.id!!)
        // Then
        assertThat(storedMember).isEqualTo(member)
    }



    @DisplayName("유저 아이디값으로 유저 찾기 성공")
    @Test fun `유저 아이디 값으로 찾기`(){

        //given
        memberPort.save(member)

        //when
        val findMember = memberPort.findById(member.id!!)

        //then
        assertThat(findMember.email).isEqualTo("wjsdbsghks96@naver.com")

    }

    @DisplayName("유저 아이디 값 실패")
    @Test fun `유저 아이디 값 찾기 실패`(){
        //given
        memberPort.save(member)

        //when
        val findMember = memberPort.findById(member.id!!)

        //then
        assertThrows<NoSuchElementException> {
            memberPort.findById(-1L)
        }
    }

    @DisplayName("유저 이메일 중복")
    @Test fun `유저 이메일 중복 체크`(){
        //given
        memberPort.save(member)

        //when
        assertThat(memberPort.duplicateEmailCheck("wjsdbsghks96@naver.com")).isTrue()
    }

    @DisplayName("닉네임 중복 성공")
    @Test fun `닉네임 중복 체크`(){
        //given
        memberPort.save(member)

        //when
        assertThat(memberPort.duplicateNickNameCheck("윤환이")).isTrue()
    }
}