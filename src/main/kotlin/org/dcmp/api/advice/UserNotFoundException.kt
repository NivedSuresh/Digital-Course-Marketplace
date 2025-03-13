package org.dcmp.api.advice

import org.dcmp.domain.exception.ErrorCode
import org.dcmp.domain.exception.GlobalException
import org.springframework.http.HttpStatus

class UserNotFoundException(message: String): GlobalException(message, ErrorCode.USER_NOT_FOUND, HttpStatus.BAD_REQUEST)
