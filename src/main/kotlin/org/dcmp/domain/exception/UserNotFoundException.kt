package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

class UserNotFoundException(message: String): GlobalException(message, ErrorCode.USER_NOT_FOUND, HttpStatus.BAD_REQUEST)
