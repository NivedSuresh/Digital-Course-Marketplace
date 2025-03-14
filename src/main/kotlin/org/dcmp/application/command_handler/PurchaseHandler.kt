package org.dcmp.application.command_handler

import org.dcmp.application.command.PurchaseCommand
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.domain.entity.Purchase
import org.dcmp.domain.exception.EntityNotFoundException
import org.dcmp.infrastructure.persistence.jpa.CourseRepository
import org.dcmp.infrastructure.persistence.jpa.PurchaseRepository
import org.springframework.stereotype.Component
import java.time.LocalDate


@Component
class PurchaseHandler(private val purchaseRepository: PurchaseRepository,
                      private val courseRepository: CourseRepository): RequestHandler<PurchaseCommand, Boolean> {

    override fun handle(request: PurchaseCommand): Boolean {
        val coursePrice = this.courseRepository.findCoursePriceById(request.courseId)
            .orElseThrow{ EntityNotFoundException("Course") }

        val purchase:  Purchase = Purchase(
            null,
            request.customerId,
            null,
            request.courseId,
            null,
            coursePrice,
            LocalDate.now()
        );

        this.purchaseRepository.save(purchase);
        return true;
    }

}
