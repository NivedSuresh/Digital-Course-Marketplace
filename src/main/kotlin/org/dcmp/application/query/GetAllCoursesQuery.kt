package org.dcmp.application.query

import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.Course
import org.springframework.data.domain.Page

data class GetAllCoursesQuery(var creatorId: Long?, val title: String, val description: String, var limit: Int = 10, var offset: Int = 0): Request<Page<Course>>
