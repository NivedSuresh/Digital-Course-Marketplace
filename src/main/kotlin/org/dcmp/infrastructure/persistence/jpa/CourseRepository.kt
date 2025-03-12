package org.dcmp.infrastructure.persistence.jpa

import org.dcmp.domain.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CourseRepository : JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c " +
           "WHERE LOWER(c.title) LIKE LOWER(CONCAT(:title, '%')) OR" +
           " LOWER(c.description) LIKE LOWER(CONCAT(:description, '%'))")
    fun findByTitleAndDescription(title: String, description: String): List<Course>

}

