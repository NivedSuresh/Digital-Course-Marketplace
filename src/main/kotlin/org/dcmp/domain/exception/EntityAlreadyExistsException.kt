package org.dcmp.domain.exception

import org.springframework.http.HttpStatus

class EntityAlreadyExistsException(entityName: String) :
    GlobalException("$entityName already exists", ErrorCode.DUPLICATE_ENTITY, HttpStatus.BAD_REQUEST)

