package org.dcmp.api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class AdminController {

    @GetMapping("/user")
    fun getAllUsers() {

    }


}
