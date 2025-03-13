package org.dcmp.api.advice

import org.dcmp.domain.exception.GlobalException
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


}
