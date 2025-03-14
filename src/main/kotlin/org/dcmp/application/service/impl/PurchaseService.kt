package org.dcmp.application.service.impl

import org.dcmp.application.command.PurchaseCommand
import org.dcmp.application.command_handler.PurchaseHandler
import org.dcmp.application.service.IPurchaseService
import org.springframework.stereotype.Service

@Service
class PurchaseService(private val purchaseHandler: PurchaseHandler): IPurchaseService {


    override fun purchase(command: PurchaseCommand): Boolean {
        return purchaseHandler.handle(command)
    }

}
