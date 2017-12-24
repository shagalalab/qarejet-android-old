package com.shagalalab.qarejet.domain.repository

import com.shagalalab.qarejet.domain.model.Account
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by atabek on 12/14/2017.
 */

interface AccountRepository {

    fun getAllAccounts(): Single<List<Account>>
    fun getAccount(id: Long): Single<Account>
    fun addAccount(account: Account): Completable
    fun addAccounts(accounts: List<Account>): Completable

}