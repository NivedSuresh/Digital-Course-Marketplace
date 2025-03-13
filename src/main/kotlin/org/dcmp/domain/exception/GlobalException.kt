package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

open class GlobalException(
    message: String,
    val errorCode: ErrorCode,
    val httpStatus: HttpStatus
) : RuntimeException(message)

