package com.shagalalab.qarejet.domain.interactor.type

interface UseCase<T> {
    fun execute(): T
}

interface UseCaseWithParameters<P, R> {
    fun execute(parameter: P) : R
}