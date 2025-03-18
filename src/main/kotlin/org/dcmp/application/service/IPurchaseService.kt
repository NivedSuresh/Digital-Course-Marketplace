package org.dcmp.application.service

import org.dcmp.application.command.PurchaseCommand
import org.dcmp.application.query.GetCustomerBoughtCoursesQuery
import org.dcmp.domain.entity.Course
import org.springframework.data.domain.Page

interface IPurchaseService {
    fun purchase(command: PurchaseCommand): Boolean
    fun getBoughtCourses(customerBoughtCoursesQuery: GetCustomerBoughtCoursesQuery): Page<Course>
}
