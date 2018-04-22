package com.shagalalab.qarejet.domain.interactor.transaction

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import org.junit.Test
import java.util.Date

class TransactionsUseCasesTest {

    private val transaction = Transaction(1, 1, Date(), Account(1, "one", "one"), Category(1, "", "icon", "color", 1), 1.2, "")
    private val transactionId = 1L
    private val repositoryMock = mock<TransactionRepository>()

    @Test
    fun shouldAddNewTransaction() {
        val useCase = AddTransactionUseCase(repositoryMock)
        useCase.execute(transaction)
        verify(repositoryMock, times(1)).addTransaction(transaction)
        verifyNoMoreInteractions(repositoryMock)
    }

    @Test
    fun shouldGetAllTransactions() {
        val useCase = GetTransactionsUseCase(repositoryMock)
        useCase.execute()
        verify(repositoryMock, times(1)).getAllTransactions()
        verifyNoMoreInteractions(repositoryMock)
    }

    @Test
    fun shouldGetTransaction() {
        val useCase = GetTransactionUseCase(repositoryMock)
        useCase.execute(transactionId)
        verify(repositoryMock, times(1)).getTransaction(transactionId)
        verifyNoMoreInteractions(repositoryMock)
    }
}