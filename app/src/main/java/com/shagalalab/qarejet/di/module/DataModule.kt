package com.shagalalab.qarejet.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_EXPENSE
import com.shagalalab.qarejet.util.Constants.TRANSACTION_TYPE_INCOME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesAccounts(context: Context) = listOf(Account(0, context.getString(R.string.account_cash), "UZS"))

    @Provides
    @Singleton
    fun providesCategories(context: Context) = listOf(
        Category(0, context.getString(R.string.category_expense_food), R.drawable.ic_local_dining_white_24dp,
            R.color.red, TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_leisure), R.drawable.ic_local_movies_white_24dp,
            R.color.purple, TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_education), R.drawable.ic_school_white_24dp,
            R.color.deep_purple, TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_shopping), R.drawable.ic_local_grocery_store_white_24dp,
            R.color.indigo, TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_transport), R.drawable.ic_directions_bus_white_24dp,
            R.color.blue, TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_bills), R.drawable.ic_receipt_white_24dp,
            R.color.teal, TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_health), R.drawable.ic_local_hospital_white_24dp,
            R.color.green, TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_vehicle), R.drawable.ic_directions_car_white_24dp,
            R.color.lime, TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_other), R.drawable.ic_monetization_on_white_24dp,
            R.color.amber, TRANSACTION_TYPE_EXPENSE),

        Category(0, context.getString(R.string.category_income_salary), R.drawable.ic_account_balance_wallet_white_24dp,
            R.color.orange, TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_gift), R.drawable.ic_card_giftcard_white_24dp,
            R.color.deep_orange, TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_rental), R.drawable.ic_business_white_24dp,
            R.color.brown, TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_sale), R.drawable.ic_shop_white_24dp,
            R.color.grey, TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_interest), R.drawable.ic_insert_chart_white_24dp,
            R.color.blue_grey, TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_other), R.drawable.ic_account_balance_white_24dp,
            R.color.light_blue, TRANSACTION_TYPE_INCOME)
    )

    @Provides
    @Singleton
    fun providesDatabase(context: Context) = Room.databaseBuilder(context, Database::class.java, "qarejet.db").build()

    @Provides
    @Singleton
    fun providesSharedPrefs(context: Context) = context.getSharedPreferences("QarejetPrefs", Context.MODE_PRIVATE)
}