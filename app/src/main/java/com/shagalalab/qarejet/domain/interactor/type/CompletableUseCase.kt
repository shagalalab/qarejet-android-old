package com.shagalalab.qarejet.domain.interactor.type

import io.reactivex.Completable

/**
 * Created by atabek on 12/16/2017.
 */

interface CompletableUseCase {
    fun execute() : Completable
}

interface CompletableUseCaseWithParameters<P> {
    fun execute(parameter: P) : Completable
}