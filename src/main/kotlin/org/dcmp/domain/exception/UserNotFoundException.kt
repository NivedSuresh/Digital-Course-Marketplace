package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

class UserNotFoundException(message: String): GlobalException(message, ErrorCode.ENTITY_NOT_FOUND, HttpStatus.BAD_REQUEST)
