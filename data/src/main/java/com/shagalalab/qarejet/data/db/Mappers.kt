package com.shagalalab.qarejet.data.db

import com.shagalalab.qarejet.data.db.model.AccountDbModel
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.data.db.model.FullTransactionModel
import com.shagalalab.qarejet.data.db.model.TransactionDbModel
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.Transaction

object DbToDomainMapper {

    fun mapTransactionList(dbModels: List<FullTransactionModel>): List<Transaction> {
        return dbModels.map { mapTransaction(it) }
    }

    fun mapTransaction(dbModel: FullTransactionModel): Transaction {
        return Transaction(dbModel.transaction.id, dbModel.transaction.type, dbModel.transaction.date,
            mapAccount(dbModel.account), mapCategory(dbModel.category), dbModel.transaction.amount, dbModel.transaction.memo)
    }

    fun mapAccountsList(dbModels: List<AccountDbModel>): List<Account> {
        return dbModels.map { mapAccount(it) }
    }

    fun mapAccount(dbModel: AccountDbModel): Account {
        return Account(dbModel.id, dbModel.title)
    }

    fun mapCategoriesList(dbModels: List<CategoryDbModel>): List<Category> {
        return dbModels.map { mapCategory(it) }
    }

    fun mapCategory(dbModel: CategoryDbModel): Category {
        return Category(dbModel.id, dbModel.title, dbModel.type)
    }

}

object DomainToDbMapper {

    fun mapTransaction(model: Transaction): TransactionDbModel {
        return TransactionDbModel(0, model.type, model.date, model.account.id,
                model.category.id, model.amount, model.memo)
    }

    fun mapAccountsList(models: List<Account>): List<AccountDbModel> {
        return models.map { mapAccount(it) }
    }

    fun mapAccount(model: Account): AccountDbModel {
        return AccountDbModel(0, model.title)
    }

    fun mapCategoriesList(models: List<Category>): List<CategoryDbModel> {
        return models.map { mapCategory(it) }
    }

    fun mapCategory(model: Category): CategoryDbModel {
        return CategoryDbModel(0, model.title, model.type)
    }

}