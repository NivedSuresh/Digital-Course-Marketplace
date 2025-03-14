package org.dcmp.application.query

import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.User
import org.springframework.data.domain.Page

class GetAllUsersQuery: Request<Page<User>>
