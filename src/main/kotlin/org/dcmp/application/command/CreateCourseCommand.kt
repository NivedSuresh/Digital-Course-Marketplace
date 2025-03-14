package org.dcmp.application.command

import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.Course

data class CreateCourseCommand(val title: String,
                               val description: String,
                               val price: Double,
                               var creatorId: Long)/* CREATOR ID WILL BE OVERWRITTEN WITH THE ID
                                                    FROM SECURITY CONTEXT UNLESS USER IS ADMIN */
    : Request<Course>
