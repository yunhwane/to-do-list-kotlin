package com.example.todolist.api.member.domain

import com.example.todolist.UserType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity(name = "users")
data class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name = "email")
        var email: String = "",

        @Column(name = "nickName")
        var nickName: String = "",

        @Column(name = "password")
        var password: String = "",

        @Column(name = "authority")
        var userType: UserType
){
        constructor(email: String, nickName: String ,password: String) : this(null, email, nickName, password, UserType.USER)
        constructor() : this(null, "", "", "", UserType.USER) {

        }

}


