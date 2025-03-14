package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

class EntityNotFoundException(entityName: String): GlobalException("$entityName not found", ErrorCode.ENTITY_NOT_FOUND, HttpStatus.BAD_REQUEST) {
}
