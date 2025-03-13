package org.dcmp.domain.contracts

interface RequestHandler<T : Request<R>, R> {
    fun handle(request: T): R
}

