package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

class UnexpectedStateException(message: String, errorCode: ErrorCode, statusCode: HttpStatus):
      GlobalException(message, errorCode, statusCode)
