package org.dcmp.application.query

import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.Course
import org.springframework.data.domain.Page

data class GetAllCoursesQuery(var creatorId: Long?, var title: String?, var description: String?, var limit: Int? = 10, var page: Int? = 1): Request<Page<Course>>
