package com.shagalalab.qarejet.domain.interactor.category

import com.shagalalab.qarejet.domain.interactor.type.SingleUseCase
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.repository.CategoryRepository
import io.reactivex.Single

class GetAllCategoriesUseCase constructor(
        private val repository: CategoryRepository
) : SingleUseCase<List<Category>> {

    override fun execute(): Single<List<Category>>  = repository.getAllCategories()

}