package org.dcmp.application.query

import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.Course
import org.springframework.data.domain.Page

class GetCustomerBoughtCoursesQuery(var principalId: Long?,
                                    var limit: Int?,
                                    var page:Int?): Request<Page<Course>>
