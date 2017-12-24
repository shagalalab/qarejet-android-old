package com.shagalalab.qarejet.data.repository

import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.db.DbToDomainMapper
import com.shagalalab.qarejet.data.db.DomainToDbMapper
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.repository.AccountRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by atabek on 12/21/2017.
 */

class AccountRepositoryImpl constructor(var database: Database) : AccountRepository {

    override fun getAllAccounts(): Single<List<Account>> {
        return database
                .accountDao
                .getAccounts()
                .map(DbToDomainMapper::mapAccountsList)
    }

    override fun getAccount(id: Long): Single<Account> {
        return database
                .accountDao
                .getAccount(id)
                .map(DbToDomainMapper::mapAccount)
    }

    override fun addAccount(account: Account): Completable {
        return Completable.fromAction({
            database
                    .accountDao
                    .insertAccount(DomainToDbMapper.mapAccount(account))
        })
    }

    override fun addAccounts(accounts: List<Account>): Completable {
        return Completable.fromAction({
            database
                    .accountDao
                    .insertAccounts(DomainToDbMapper.mapAccounts(accounts))
        })
    }

}