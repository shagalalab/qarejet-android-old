package com.shagalalab.qarejet.domain.interactor.type

import io.reactivex.Single

/**
 * Created by atabek on 12/16/2017.
 */

interface SingleUseCase<T> {
    fun execute() : Single<T>
}

interface SingleUserCaseWithParameters<P, R> {
    fun execute(parameter: P) : Single<R>
}