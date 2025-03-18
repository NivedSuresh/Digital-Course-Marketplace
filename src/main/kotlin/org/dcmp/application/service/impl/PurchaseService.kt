package org.dcmp.application.service.impl

import org.dcmp.application.command.PurchaseCommand
import org.dcmp.application.command_handler.PurchaseHandler
import org.dcmp.application.query.GetCustomerBoughtCoursesQuery
import org.dcmp.application.query_handler.CustomerBoughtCoursesHandler
import org.dcmp.application.service.IPurchaseService
import org.dcmp.domain.entity.Course
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PurchaseService(private val purchaseHandler: PurchaseHandler, private val customerBoughtCoursesHandler: CustomerBoughtCoursesHandler): IPurchaseService {


    override fun purchase(command: PurchaseCommand): Boolean {
        return purchaseHandler.handle(command)
    }

    override fun getBoughtCourses(customerBoughtCoursesQuery: GetCustomerBoughtCoursesQuery): Page<Course> {
        return customerBoughtCoursesHandler.handle(customerBoughtCoursesQuery)
    }

}
