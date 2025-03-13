package org.dcmp.api.advice

import org.dcmp.domain.exception.ErrorCode
import org.dcmp.domain.exception.GlobalException
import org.springframework.http.HttpStatus

class InvalidCredentialsException(errorCode: ErrorCode): GlobalException("Invalid Credentials", errorCode, HttpStatus.BAD_REQUEST)
