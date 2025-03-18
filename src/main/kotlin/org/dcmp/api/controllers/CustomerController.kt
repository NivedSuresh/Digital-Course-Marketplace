package org.dcmp.api.controllers

import org.dcmp.application.command.PurchaseCommand
import org.dcmp.application.dto.CourseDto
import org.dcmp.application.mapper.CourseMapper
import org.dcmp.application.query.GetCustomerBoughtCoursesQuery
import org.dcmp.application.service.IPurchaseService
import org.dcmp.application.service.impl.PurchaseService
import org.dcmp.domain.contracts.PagedResult
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/customer")
class CustomerController(private val purchaseService: IPurchaseService) {


    @PostMapping("/buy/course/{courseId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun buy(@PathVariable courseId: Long): Map<String, Boolean> {
        val customerId = SecurityContextHolder.getContext().authentication.name.toLong()
        return mapOf("success" to purchaseService.purchase(PurchaseCommand(courseId, customerId)))
    }


    @GetMapping("/course")
    fun getBoughtCourses(@ModelAttribute getCustomerBoughtCoursesQuery: GetCustomerBoughtCoursesQuery, @AuthenticationPrincipal principal: UserDetails): PagedResult<CourseDto> {
        getCustomerBoughtCoursesQuery.principalId = principal.username.toLong();

        val courses = this.purchaseService.getBoughtCourses(getCustomerBoughtCoursesQuery)

        return PagedResult(
            items = courses.content.map { course -> CourseMapper.toDto(course) },
            totalRecords = courses.totalElements,
            limit = courses.size.toLong(),
            offset = (courses.number * courses.size)
        )
    }



}
