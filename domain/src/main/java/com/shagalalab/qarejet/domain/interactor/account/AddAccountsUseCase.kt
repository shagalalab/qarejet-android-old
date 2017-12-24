package com.shagalalab.qarejet.domain.interactor.account

import com.shagalalab.qarejet.domain.interactor.type.CompletableUseCaseWithParameters
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.repository.AccountRepository
import io.reactivex.Completable

/**
 * Created by atabek on 12/21/2017.
 */

class AddAccountsUseCase(
        private val repository: AccountRepository
) : CompletableUseCaseWithParameters<List<Account>> {

    override fun execute(parameter: List<Account>): Completable {
        return repository.addAccounts(parameter)
    }

}