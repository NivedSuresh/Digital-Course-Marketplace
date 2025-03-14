package org.dcmp.api.controllers

import org.dcmp.application.command.PurchaseCommand
import org.dcmp.application.service.IPurchaseService
import org.dcmp.application.service.impl.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/customer")
class CustomerController(private val purchaseService: IPurchaseService) {


    @PostMapping("/buy/course/{courseId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun buy(@PathVariable courseId: Long): Boolean {
        val customerId = SecurityContextHolder.getContext().authentication.name.toLong()
        return {"success" : purchaseService.purchase(PurchaseCommand(courseId, customerId))};
    }


}
