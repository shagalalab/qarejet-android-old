package com.shagalalab.qarejet.domain.interactor.account

import com.shagalalab.qarejet.domain.interactor.type.CompletableUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.Account
import io.reactivex.Completable

/**
 * Created by atabek on 12/21/2017.
 */

class AddAccountsUseCase() : CompletableUseCaseWithParameters<List<Account>> {

    override fun execute(parameter: List<Account>): Completable {
        return Completable.complete()
    }

}