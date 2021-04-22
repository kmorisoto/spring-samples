package com.example.owasptest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.csrf.CookieCsrfTokenRepository


@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class OwaspTestApplication

fun main(args: Array<String>) {
    runApplication<OwaspTestApplication>(*args)
}


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest()
            .permitAll()

        http.formLogin().disable().logout().disable()


        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        http // AUTHORIZE
//            .authorizeRequests()
//            .mvcMatchers("/**")
//            .permitAll()
////            .anyRequest()
////            .authenticated()
////            .and()
////            .and() // EXCEPTION
////            .exceptionHandling()
//            .and() // LOGIN
//            .httpBasic().disable()
//            .formLogin().disable()
//            .logout().disable()
//            .csrf() //.disable()
            //.ignoringAntMatchers("/login")
//            .csrfTokenRepository(CookieCsrfTokenRepository())
    }

//    @Autowired
//    @Throws(Exception::class)
//    fun configureGlobal(
//        auth: AuthenticationManagerBuilder,
//        @Qualifier("simpleUserDetailsService") userDetailsService: UserDetailsService,
//        passwordEncoder: PasswordEncoder?
//    ) {
//        auth.eraseCredentials(true)
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(passwordEncoder)
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
//
//    fun authenticationEntryPoint(): AuthenticationEntryPoint {
//        return SimpleAuthenticationEntryPoint()
//    }
//
//    fun accessDeniedHandler(): AccessDeniedHandler {
//        return SimpleAccessDeniedHandler()
//    }
//
//    fun authenticationSuccessHandler(): AuthenticationSuccessHandler {
//        return SimpleAuthenticationSuccessHandler()
//    }
//
//    fun authenticationFailureHandler(): AuthenticationFailureHandler {
//        return SimpleAuthenticationFailureHandler()
//    }
//
//    fun logoutSuccessHandler(): LogoutSuccessHandler {
//        return HttpStatusReturningLogoutSuccessHandler()
//    }
}
