package com.shagalalab.qarejet.domain.interactor.category

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.repository.CategoryRepository
import org.junit.Test

class CategoriesUseCasesTest {

    private val categoriesList = listOf(Category(1, "one", 1), Category(2, "two", 1))
    private val repositoryMock = mock<CategoryRepository>()

    @Test
    fun shouldAddCategories() {
        val useCase = AddCategoriesUseCase(repositoryMock)
        useCase.execute(categoriesList)
        verify(repositoryMock, times(1)).addCategories(categoriesList)
        verifyNoMoreInteractions(repositoryMock)
    }

    @Test
    fun shouldGetAllCategories() {
        val useCase = GetAllCategoriesUseCase(repositoryMock)
        useCase.execute()
        verify(repositoryMock, times(1)).getAllCategories()
        verifyNoMoreInteractions(repositoryMock)
    }
}