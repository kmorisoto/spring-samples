package com.example.springerrorpage

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TestHandler: HandlerInterceptor  {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        throw NullPointerException("interceptor")
        return super.preHandle(request, response, handler)
    }
}

@Configuration
class HandlerConfig(
    private val testHandler: TestHandler
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(testHandler)
            .addPathPatterns("/interceptor")
    }
}
