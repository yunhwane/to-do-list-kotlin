package com.example.todolist.global.config

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain{

        http.addFilterBefore(loggingFilter(), BasicAuthenticationFilter::class.java)

        http.csrf {
            it.disable()
        }

        http.authorizeHttpRequests {
            it
                .requestMatchers("/api/v1/**").permitAll()
                .requestMatchers("/ping").permitAll()
                .anyRequest().authenticated()
        }

        http.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.formLogin {
            it.disable()
        }

        return http.build()
    }
}

class loggingFilter : Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val req = request as HttpServletRequest
        val res = response as HttpServletResponse

        val path = req.requestURI
        val method = req.method

        val startTime = System.currentTimeMillis()
        chain?.doFilter(request, response)
        val endTime = System.currentTimeMillis()

        val log = "[${method}] ${path} (${endTime - startTime}ms)"
        println(log)
    }
}

