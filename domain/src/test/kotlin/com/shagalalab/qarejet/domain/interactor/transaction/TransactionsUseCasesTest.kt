package com.shagalalab.qarejet.domain.interactor.transaction

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.domain.repository.TransactionRepository
import org.junit.Test
import java.util.*

class TransactionsUseCasesTest {

    private val transaction = Transaction(1, 1, Date(), 1, 1, 1.2, "")
    private val transactionId = 1L
    private val repositoryMock = mock<TransactionRepository>()

    @Test
    fun shouldAddNewTransaction() {
        val useCase = AddNewTransactionUseCase(repositoryMock)
        useCase.execute(transaction)
        verify(repositoryMock, times(1)).addTransaction(transaction)
        verifyNoMoreInteractions(repositoryMock)
    }

    @Test
    fun shouldGetAllTransactions() {
        val useCase = GetAllTransactionsUseCase(repositoryMock)
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