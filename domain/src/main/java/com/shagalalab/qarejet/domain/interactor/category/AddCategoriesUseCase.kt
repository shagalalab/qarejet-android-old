package com.shagalalab.qarejet.domain.interactor.category

import com.shagalalab.qarejet.core.utils.Category
import com.shagalalab.qarejet.domain.interactor.type.CompletableUseCaseWithParameters
import com.shagalalab.qarejet.domain.repository.CategoryRepository
import io.reactivex.Completable

class AddCategoriesUseCase constructor(
        private val categoryRepository: CategoryRepository
) : CompletableUseCaseWithParameters<List<Category>> {

    override fun execute(parameter: List<Category>): Completable = categoryRepository.addCategories(parameter)

}