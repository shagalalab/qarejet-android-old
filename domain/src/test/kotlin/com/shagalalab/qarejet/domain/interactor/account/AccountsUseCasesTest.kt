package com.shagalalab.qarejet.domain.interactor.account

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.shagalalab.qarejet.domain.model.Account
import com.shagalalab.qarejet.domain.repository.AccountRepository
import org.junit.Test

class AccountsUseCasesTest {

    private val accountsList = listOf(Account(1, "one"), Account(2, "two"))
    private val repositoryMock = mock<AccountRepository>()

    @Test
    fun shouldAddAccounts() {
        val useCase = AddAccountsUseCase(repositoryMock)
        useCase.execute(accountsList)
        verify(repositoryMock, times(1)).addAccounts(accountsList)
        verifyNoMoreInteractions(repositoryMock)
    }

    @Test
    fun shouldGetAllAccounts() {
        val useCase = GetAllAccountsUseCase(repositoryMock)
        useCase.execute()
        verify(repositoryMock, times(1)).getAllAccounts()
        verifyNoMoreInteractions(repositoryMock)
    }
}