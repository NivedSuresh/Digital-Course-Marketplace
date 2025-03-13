package org.dcmp.domain.contracts.cqrs

interface RequestHandler<T : Request<R>, R> {
    fun handle(request: T): R
}

