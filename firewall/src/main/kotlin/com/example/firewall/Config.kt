package com.example.firewall

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.firewall.HttpFirewall
import org.springframework.security.web.firewall.StrictHttpFirewall

@Configuration(proxyBeanMethods = false)
class Config: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.formLogin().disable()
    }

    @Bean
    fun httpFirewall(): HttpFirewall = StrictHttpFirewall().apply {
        setAllowedHttpMethods(listOf( "POST"))
    }
}
