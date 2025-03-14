package org.dcmp.api.advice

import org.dcmp.domain.exception.ErrorCode
import org.dcmp.domain.exception.GlobalException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private const val DEFAULT_ERROR_MESSAGE = "An unexpected error occurred"
    }

    @ExceptionHandler(GlobalException::class)
    fun handleGlobalException(e: GlobalException): ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(e.message ?: DEFAULT_ERROR_MESSAGE, e.errorCode)
        return ResponseEntity.status(e.httpStatus).body(errorResponse)
    }


    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(DEFAULT_ERROR_MESSAGE, ErrorCode.UNKNOWN_ERROR)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }


}
