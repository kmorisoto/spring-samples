package com.example.springerrorpage

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.IllegalArgumentException

@ControllerAdvice
class ExceptionHandlingController {

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegal(): String = "error/illegal"

    @ExceptionHandler(NullPointerException::class)
    fun npe(): String = "error/npe"
}
