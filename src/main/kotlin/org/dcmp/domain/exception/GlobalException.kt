package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

class GlobalException(
    val errorCode: ErrorCode,
    val httpStatus: HttpStatus,
    message: String
) : RuntimeException(message)

