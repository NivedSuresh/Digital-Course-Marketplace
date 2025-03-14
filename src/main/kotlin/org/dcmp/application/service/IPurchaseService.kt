package org.dcmp.application.service

import org.dcmp.application.command.PurchaseCommand

interface IPurchaseService {
    fun purchase(command: PurchaseCommand): Boolean
}
