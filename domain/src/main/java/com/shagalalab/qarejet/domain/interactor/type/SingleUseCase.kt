package com.shagalalab.qarejet.domain.interactor.type

import io.reactivex.Single

interface SingleUseCase<T> {
    fun execute() : Single<T>
}

interface SingleUseCaseWithParameters<P, R> {
    fun execute(parameter: P) : Single<R>
}