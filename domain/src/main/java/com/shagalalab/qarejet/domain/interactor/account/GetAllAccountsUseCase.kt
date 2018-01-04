package com.shagalalab.qarejet.domain.interactor.account

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCase
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.repository.AccountRepository
import io.reactivex.Single

class GetAllAccountsUseCase constructor(
        private val repository: AccountRepository
) : SingleUseCase<List<Account>> {

    override fun execute(): Single<List<Account>>  = repository.getAllAccounts()

}