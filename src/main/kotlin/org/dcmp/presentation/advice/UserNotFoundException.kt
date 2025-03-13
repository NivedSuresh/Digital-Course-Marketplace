package org.dcmp.presentation.advice

import org.dcmp.domain.exception.ErrorCode
import org.dcmp.domain.exception.GlobalException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class UserNotFoundException(message: String): GlobalException(message, ErrorCode.USER_NOT_FOUND, HttpStatus.BAD_REQUEST)
