package com.shagalalab.qarejet.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
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
    fun providesAccounts(context: Context) = listOf(Account(0, context.getString(R.string.account_cash), "UZS"),
        Account(1L, context.getString(R.string.account_card), "UZS"))

    @Provides
    @Singleton
    fun providesCategories(context: Context) = listOf(
        Category(0, context.getString(R.string.category_expense_food), "ic_local_dining_white_24dp", "red", TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_leisure), "ic_local_movies_white_24dp", "purple", TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_education), "ic_school_white_24dp", "deep_purple", TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_shopping), "ic_local_grocery_store_white_24dp", "indigo", TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_transport), "ic_directions_bus_white_24dp", "blue", TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_bills), "ic_receipt_white_24dp", "teal", TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_health), "ic_local_hospital_white_24dp", "green", TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_vehicle), "ic_directions_car_white_24dp", "lime", TRANSACTION_TYPE_EXPENSE),
        Category(0, context.getString(R.string.category_expense_other), "ic_monetization_on_white_24dp", "amber", TRANSACTION_TYPE_EXPENSE),

        Category(0, context.getString(R.string.category_income_salary), "ic_account_balance_wallet_white_24dp", "orange", TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_gift), "ic_card_giftcard_white_24dp", "deep_orange", TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_rental), "ic_business_white_24dp", "brown", TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_sale), "ic_shop_white_24dp", "grey", TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_interest), "ic_insert_chart_white_24dp", "blue_grey", TRANSACTION_TYPE_INCOME),
        Category(0, context.getString(R.string.category_income_other), "ic_account_balance_white_24dp", "light_blue", TRANSACTION_TYPE_INCOME)
    )

    @Provides
    @Singleton
    fun providesDatabase(context: Context) =
        Room.databaseBuilder(context, Database::class.java, "qarejet.db").build()

    @Provides
    @Singleton
    fun providesSharedPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences("QarejetPrefs", Context.MODE_PRIVATE)
}