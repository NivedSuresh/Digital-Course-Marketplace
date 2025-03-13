package org.dcmp.api.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController("creator")
class CreatorController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("course")
    fun createCourse() {
        logger.info("Creating course")
    }

}
