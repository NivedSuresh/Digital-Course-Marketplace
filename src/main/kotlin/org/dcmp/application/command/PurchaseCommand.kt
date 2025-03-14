package org.dcmp.application.command

import org.dcmp.domain.contracts.Request

class PurchaseCommand(val courseId: Long, val customerId: Long): Request<Boolean>
