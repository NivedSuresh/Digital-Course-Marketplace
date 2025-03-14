package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

class PurchaseConstraintException(message : String, errorCode: ErrorCode): GlobalException(message, errorCode, HttpStatus.BAD_REQUEST)
