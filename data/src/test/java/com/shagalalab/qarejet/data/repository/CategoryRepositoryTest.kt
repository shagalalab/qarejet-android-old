package com.shagalalab.qarejet.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.shagalalab.qarejet.data.db.CategoryDao
import com.shagalalab.qarejet.data.db.Database
import com.shagalalab.qarejet.data.db.model.CategoryDbModel
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.repository.CategoryRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class CategoryRepositoryTest {

    private val db = mock<Database>()
    private val categoryDao = mock<CategoryDao>()
    private val categoryDbModel = CategoryDbModel(1, "one", "icon_one", "color_one", 1)
    private val categoryDbModelList = listOf(categoryDbModel, CategoryDbModel(2, "two", "icon_two", "color_two", 2))
    private val category = Category(1, "one", "icon_one", "color_one", 1)
    private val categoryList = listOf(category, Category(2, "two", "icon_two", "color_two", 2))
    private val categoryId = 1L
    private val singleCategoryDbModel = Single.just(categoryDbModel)
    private val singleCategoryDbModelList = Single.just(categoryDbModelList)
    private lateinit var repository : CategoryRepository

    @Before
    fun setUp() {
        repository = CategoryRepositoryImpl(db)
        whenever(db.categoryDao).thenReturn(categoryDao)
    }

    @Test
    fun shouldGetAllCategories() {
        whenever(categoryDao.getCategories()).thenReturn(singleCategoryDbModelList)
        val test = repository.getAllCategories().test()
        test.assertNoErrors()
        test.assertValue({ l -> l == categoryList })
    }

    @Test
    fun shouldGetCategory() {
        whenever(categoryDao.getCategory(categoryId)).thenReturn(singleCategoryDbModel)
        val test = repository.getCategory(categoryId).test()
        test.assertNoErrors()
        test.assertValue({ l -> l == category })
    }

    @Test
    fun shouldAddCategory() {
        val test = repository.addCategory(category).test()
        test.assertNoErrors()
        test.assertComplete()
    }

    @Test
    fun shouldAddCategoryList() {
        val test = repository.addCategories(categoryList).test()
        test.assertNoErrors()
        test.assertComplete()
    }
}