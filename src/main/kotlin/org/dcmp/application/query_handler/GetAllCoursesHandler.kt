package org.dcmp.application.query_handler

import jakarta.persistence.criteria.Predicate
import org.dcmp.application.query.GetAllCoursesQuery
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.domain.entity.Course
import org.dcmp.infrastructure.persistence.jpa.CourseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component

@Component
class GetAllCoursesHandler(private val courseRepository: CourseRepository) : RequestHandler<GetAllCoursesQuery, Page<Course>> {

    override fun handle(request: GetAllCoursesQuery): Page<Course> {
        request.offset = maxOf(request.offset, 0)
        request.limit = request.limit.coerceIn(10, 100)
        val pageNumber = request.offset / request.limit
        val pageable: Pageable = PageRequest.of(pageNumber, request.limit)

        val specification = buildSpecification(request.creatorId, request.title, request.description)
        return courseRepository.findAll(specification, pageable)
    }

    private fun buildSpecification(creatorId: Long?, title: String, description: String): Specification<Course> {
        return Specification { root, query, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            if (creatorId != null) {
                predicates.add(criteriaBuilder.equal(root.get<Long>("creatorId"), creatorId))
            }
            if (title.isNotBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.lowercase() + "%"))
            }
            if (description.isNotBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.lowercase() + "%"))
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }
}
