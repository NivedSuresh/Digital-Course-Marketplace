package org.dcmp.application.query_handler

import org.dcmp.application.query.GetCustomerBoughtCoursesQuery
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.domain.entity.Course
import org.dcmp.infrastructure.persistence.jpa.CourseRepository
import org.dcmp.infrastructure.persistence.jpa.PurchaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class CustomerBoughtCoursesHandler(private val purchaseRepository: PurchaseRepository): RequestHandler<GetCustomerBoughtCoursesQuery, Page<Course>> {

    override fun handle(request: GetCustomerBoughtCoursesQuery): Page<Course> {
        if (request.page == null) {
            request.page = 1
        }
        if (request.limit == null){
            request.limit = 10
        }


        request.page = maxOf(request.page!!, 1)
        request.limit = request.limit!!.coerceIn(1, 100)
        val pageable: Pageable = PageRequest.of(request.page!! - 1, request.limit!!)

        return this.purchaseRepository.findAllPurchasedCourseIdByCustomerId(request.principalId!!, pageable);
    }


}
