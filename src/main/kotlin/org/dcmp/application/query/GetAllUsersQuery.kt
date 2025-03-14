package org.dcmp.application.query

import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.Role
import org.dcmp.domain.entity.User
import org.springframework.data.domain.Page

class GetAllUsersQuery(var limit: Int = 10, var offset: Int = 0, var roles: MutableList<Role>?): Request<Page<User>>
