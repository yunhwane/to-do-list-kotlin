package com.example.todolist.api.member.service
import com.example.todolist.api.member.domain.Member
import com.example.todolist.api.member.port.MemberPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1")
class MemberService @Autowired constructor(private val memberPort: MemberPort){

    @PostMapping("/users")
    @Transactional
    fun createUser(@RequestBody memberRequest: MemberRequest){

        if (memberPort.duplicateEmailCheck(memberRequest.email)) {
            throw DataIntegrityViolationException("Email already exists: ${memberRequest.email}")
        }

        if(memberPort.duplicateNickNameCheck(memberRequest.nickName)) {
            throw DataIntegrityViolationException("NickName already exists: ${memberRequest.nickName}")
        }

        val newMember = Member(memberRequest.email,memberRequest.nickName,memberRequest.password)
        memberPort.save(newMember)
    }
}