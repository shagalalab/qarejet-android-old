package com.shagalalab.qarejet.domain.interactor.config

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.shagalalab.qarejet.domain.repository.ConfigRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.`when`

class ConfigUseCasesTest {

    private val repositoryMock = mock<ConfigRepository>()

    @Test
    fun shouldReturnTrueFlag() {
        val useCase = InitialDataUseCase(repositoryMock)
        `when`(repositoryMock.checkInitialDataPopulated()).thenReturn(true)
        assertTrue(useCase.isDataPopulated())
    }

    @Test
    fun shouldReturnFalseFlag() {
        val useCase = InitialDataUseCase(repositoryMock)
        `when`(repositoryMock.checkInitialDataPopulated()).thenReturn(false)
        assertFalse(useCase.isDataPopulated())
    }

    @Test
    fun shouldSetInitialDataPopulatedFlag() {
        val useCase = InitialDataUseCase(repositoryMock)
        useCase.setInitialDataPopulated()
        verify(repositoryMock, times(1)).setInitialDataPopulated()
        verifyNoMoreInteractions(repositoryMock)
    }

}