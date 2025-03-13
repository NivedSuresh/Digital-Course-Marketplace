package org.dcmp.api.advice

import org.dcmp.domain.exception.ErrorCode
import org.dcmp.domain.exception.GlobalException
import org.springframework.http.HttpStatus

class UnexpectedStateException(message: String, errorCode: ErrorCode, statusCode: HttpStatus):
      GlobalException(message, errorCode, statusCode)
