package org.dcmp.api.advice

import org.dcmp.domain.exception.ErrorCode

data class ErrorResponseDto(val message: String, val errorCode: ErrorCode) {

}
