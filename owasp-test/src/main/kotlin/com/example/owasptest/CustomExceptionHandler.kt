package com.example.owasptest

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

//
//@RestControllerAdvice
//class CustomExceptionHandler:ResponseEntityExceptionHandler() {
//
//    @ExceptionHandler(Exception::class)
//    fun handle404(ex: Exception?, request: WebRequest?): ResponseEntity<Any?>? {
//        val headers = HttpHeaders()
//        val body = "Not Found"
//        val status = HttpStatus.INTERNAL_SERVER_ERROR
//        return handleExceptionInternal(ex!!, body, headers, status, request!!)
//    }
//}

@RestControllerAdvice
class ApiGlobalExceptionHandler : ResponseEntityExceptionHandler() {
    // omitted
    // (1)

//    override fun handleNoHandlerFoundException(
//        ex: NoHandlerFoundException,
//        headers: HttpHeaders,
//        status: HttpStatus,
//        request: WebRequest
//    ): ResponseEntity<Any> {
//        return ResponseEntity.badRequest().build()
////        return super.handleNoHandlerFoundException(ex, headers, HttpStatus.INTERNAL_SERVER_ERROR, request)
//    }
}
