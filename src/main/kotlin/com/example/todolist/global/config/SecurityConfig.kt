package com.example.todolist.global.config


import com.example.todolist.logger
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
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain{

        http.addFilterBefore(LoggingFilter(), BasicAuthenticationFilter::class.java)

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

class LoggingFilter : Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val req = request as HttpServletRequest
        val res = response as HttpServletResponse

        // 현재 사용자 정보 가져오기
        val authentication = SecurityContextHolder.getContext().authentication

        // 사용자 정보가 있다면 로그에 포함시키기
        val userInfo = if (authentication != null && authentication.isAuthenticated) {
            "User: ${authentication.name}, Roles: ${authentication.authorities.joinToString(", ")}"
        } else {
            "Anonymous User"
        }


        val path = req.requestURI
        val method = req.method

        val startTime = System.currentTimeMillis()
        chain?.doFilter(request, response)
        val endTime = System.currentTimeMillis()

        val log = "[${method}] ${path} (${endTime - startTime}ms), $userInfo"

        logger().info(log)
    }
}



