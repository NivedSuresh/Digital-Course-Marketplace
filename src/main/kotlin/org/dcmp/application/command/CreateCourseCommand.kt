package org.dcmp.application.command

import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.Course

import jakarta.validation.constraints.*

data class CreateCourseCommand(
    @field:NotBlank(message = "Title cannot be blank")
    @field:Size(max = 255, message = "Title must not exceed 255 characters")
    val title: String,

    @field:NotBlank(message = "Description cannot be blank")
    @field:Size(max = 1000, message = "Description must not exceed 1000 characters")
    val description: String,

    @field:Positive(message = "Price must be a positive value")
    val price: Double,

    var creatorId: Long? // Will be overwritten from security context unless user is an admin
) : Request<Course>

