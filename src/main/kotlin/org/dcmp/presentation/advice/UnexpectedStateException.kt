package org.dcmp.presentation.advice

import org.dcmp.domain.exception.ErrorCode
import org.dcmp.domain.exception.GlobalException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class UnexpectedStateException(message: String, errorCode: ErrorCode, statusCode: HttpStatus):
      GlobalException(message, errorCode, statusCode)
