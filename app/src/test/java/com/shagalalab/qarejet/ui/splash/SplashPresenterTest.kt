package com.shagalalab.qarejet.ui.splash

import com.nhaarman.mockito_kotlin.*
import com.shagalalab.qarejet.TestSchedulers
import com.shagalalab.qarejet.domain.interactor.account.AddAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.AddCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.config.InitialDataUseCase
import com.shagalalab.qarejet.domain.repository.ConfigRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class SplashPresenterTest {

    @Mock private lateinit var view : SplashView
    @Mock private lateinit var addAccountsUseCase : AddAccountsUseCase
    @Mock private lateinit var addCategoriesUseCase : AddCategoriesUseCase

    @Mock private lateinit var configRepository : ConfigRepository
    @Spy
    @InjectMocks private lateinit var initialDataUseCase : InitialDataUseCase

    private val schedulersProvider = TestSchedulers()
    private lateinit var presenter: SplashPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SplashPresenter(initialDataUseCase, addAccountsUseCase, addCategoriesUseCase, schedulersProvider)
        presenter.init(view)
    }

    @Test
    fun shouldGoToNextScreenIfDataExists() {
        whenever(initialDataUseCase.isDataPopulated()).thenReturn(true)
        presenter.checkDataPopulated(arrayOf(), arrayOf(), arrayOf())

        verify(view, times(1)).goToNextScreen()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldPopulateDataIfNoDataExists() {
        whenever(initialDataUseCase.isDataPopulated()).thenReturn(false)
        whenever(addAccountsUseCase.execute(listOf())).thenReturn(Completable.complete())
        whenever(addCategoriesUseCase.execute(listOf())).thenReturn(Completable.complete())
        presenter.checkDataPopulated(arrayOf(), arrayOf(), arrayOf())

        verify(initialDataUseCase, times(1)).setInitialDataPopulated()
        verify(view, times(1)).goToNextScreen()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldShowErrorIfPopulatingDataIsFailed() {
        whenever(initialDataUseCase.isDataPopulated()).thenReturn(false)
        whenever(addAccountsUseCase.execute(listOf())).thenReturn(Completable.error(Throwable()))
        whenever(addCategoriesUseCase.execute(listOf())).thenReturn(Completable.complete())
        presenter.checkDataPopulated(arrayOf(), arrayOf(), arrayOf())

        verify(view, times(1)).showError(any())
        verifyNoMoreInteractions(view)
    }

}