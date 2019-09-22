package com.shagalalab.qarejet.ui.transaction

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.shagalalab.qarejet.TestSchedulers
import com.shagalalab.qarejet.core.utils.Category
import com.shagalalab.qarejet.domain.interactor.account.GetAllAccountsUseCase
import com.shagalalab.qarejet.domain.interactor.category.GetAllCategoriesUseCase
import com.shagalalab.qarejet.domain.interactor.transaction.AddTransactionUseCase
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.AccountRepository
import com.shagalalab.qarejet.util.Constants
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.Date

class AddTransactionPresenterTest {

    private val accounts = listOf(Account(1, "one", "one"), Account(2, "two", "two"))
    private val categories = listOf(Category(1, "one", "icon_one", "color_one", 1), Category(2, "two", "icon_two", "color_two", 2))
    private val transaction = Transaction(1, 1, Date(), accounts[0], categories[0], 1.0, "")
    private val schedulersProvider = TestSchedulers()

    @Mock private lateinit var view: AddTransactionView
    @Mock private lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase
    @Mock private lateinit var addTransactionsUseCase: AddTransactionUseCase
    @Mock private lateinit var accountRepository: AccountRepository
    @InjectMocks private lateinit var getAllAccountsUseCase: GetAllAccountsUseCase

    private lateinit var presenter: AddTransactionPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = AddTransactionPresenter(addTransactionsUseCase, getAllAccountsUseCase, getAllCategoriesUseCase, schedulersProvider)
        presenter.init(view)
    }

    @Test
    fun shouldGetAccounts() {
        whenever(getAllAccountsUseCase.execute()).thenReturn(Single.just(accounts))
        presenter.requestAccountData()

        verify(view, times(1)).updateAccounts(accounts)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldGetCategories() {
        whenever(getAllCategoriesUseCase.execute()).thenReturn(Single.just(categories))
        presenter.requestCategoryData()

        verify(view, times(1)).updateCategories(categories)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldSetTransactionTypeToIncome() {
        presenter.setTransactionTypeToIncome()
        verify(view, times(1)).setSignToPlus()
        verify(view, times(1)).filterCategories(Constants.TRANSACTION_TYPE_INCOME)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldSetTransactionTypeToExpense() {
        presenter.setTransactionTypeToExpense()
        verify(view, times(1)).setSignToMinus()
        verify(view, times(1)).filterCategories(Constants.TRANSACTION_TYPE_EXPENSE)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldChooseDate() {
        presenter.chooseDate()
        verify(view, times(1)).showDateChooser()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldChooseTime() {
        presenter.chooseTime()
        verify(view, times(1)).showTimeChooser()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldShowErrorIfNewTransactionAmountIsZero() {
        presenter.addNewTransaction(0.0, transaction.account, transaction.category, transaction.memo, transaction.date)
        verify(view, times(1)).showError(anyInt())
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldAddNewTransaction() {
        whenever(addTransactionsUseCase.execute(any())).thenReturn(Completable.complete())
        presenter.addNewTransaction(transaction.amount, transaction.account, transaction.category, transaction.memo, transaction.date)
        verify(view, times(1)).finishActivity()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldShowErrorIfAddNewTransactionIsFailed() {
        whenever(addTransactionsUseCase.execute(any())).thenReturn(Completable.error(Throwable()))
        presenter.addNewTransaction(transaction.amount, transaction.account, transaction.category, transaction.memo, transaction.date)
        verify(view, times(1)).showError(anyString())
        verifyNoMoreInteractions(view)
    }
}