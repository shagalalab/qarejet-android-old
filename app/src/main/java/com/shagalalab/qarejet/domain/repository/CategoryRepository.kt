package com.shagalalab.qarejet.domain.repository

import com.shagalalab.qarejet.domain.model.Category
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by atabek on 12/14/2017.
 */

interface CategoryRepository {

    fun getAllCategories(): Single<List<Category>>
    fun getCategory(id: Long): Single<Category>
    fun addCategory(category: Category): Completable

}