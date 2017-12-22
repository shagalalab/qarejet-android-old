package com.shagalalab.qarejet.data.db

import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.Transaction

/**
 * Created by atabek on 12/20/2017.
 */

object DbToDomainMapper {

    fun mapTransactionList(dbModels: List<TransactionDbModel>) : List<Transaction> {
        return dbModels.map { mapTransaction(it) }
    }

    fun mapTransaction(dbModel: TransactionDbModel) : Transaction {
        return Transaction(dbModel.id, dbModel.type, dbModel.date, dbModel.accountId,
                dbModel.categoryId, dbModel.amount, dbModel.memo)
    }

    fun mapAccountsList(dbModels: List<AccountDbModel>) : List<Account> {
        return dbModels.map { mapAccount(it) }
    }

    fun mapAccount(dbModel: AccountDbModel) : Account {
        return Account(dbModel.id, dbModel.title)
    }

    fun mapCategories(dbModel: List<CategoryDbModel>) : List<Category> {
        return dbModel.map { mapCategory(it) }
    }

    fun mapCategory(dbModel: CategoryDbModel) : Category {
        return Category(dbModel.id, dbModel.title)
    }

}

object DomainToDbMapper {

    fun mapTransaction(model: Transaction) : TransactionDbModel {
        return TransactionDbModel(0, model.type, model.date, model.accountId,
                model.categoryId, model.amount, model.memo)
    }

    fun mapAccount(model: Account) : AccountDbModel {
        return AccountDbModel(0, model.title)
    }

    fun mapCategory(model: Category) : CategoryDbModel {
        return CategoryDbModel(0, model.title)
    }

}