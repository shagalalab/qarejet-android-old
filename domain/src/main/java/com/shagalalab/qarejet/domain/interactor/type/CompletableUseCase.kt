package com.shagalalab.qarejet.domain.interactor.type

import io.reactivex.Completable

interface CompletableUseCase {
    fun execute() : Completable
}

interface CompletableUseCaseWithParameters<P> {
    fun execute(parameter: P) : Completable
}