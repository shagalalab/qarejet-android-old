package com.shagalalab.qarejet.domain.repository

import com.shagalalab.qarejet.core.utils.Category
import io.reactivex.Completable
import io.reactivex.Single

interface CategoryRepository {

    fun getAllCategories(): Single<List<Category>>
    fun getCategory(id: Long): Single<Category>
    fun addCategory(category: Category): Completable
    fun addCategories(categories: List<Category>): Completable

}