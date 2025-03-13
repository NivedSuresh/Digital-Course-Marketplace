package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

class InvalidCredentialsException(errorCode: ErrorCode): GlobalException("Invalid Credentials", errorCode, HttpStatus.BAD_REQUEST)
