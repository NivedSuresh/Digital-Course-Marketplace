package org.dcmp.application.dto

data class CourseDto(val id : Long, val title: String, val description: String?, val amount: Double, val creatorId: Long)
