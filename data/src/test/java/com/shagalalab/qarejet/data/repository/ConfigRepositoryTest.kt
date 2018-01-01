package com.shagalalab.qarejet.data.repository

import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.shagalalab.qarejet.data.repository.ConfigRepositoryImpl.Companion.INITIAL_DATA_POPULATED
import com.shagalalab.qarejet.domain.repository.ConfigRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class ConfigRepositoryTest {

    private val sharedPref = mock<SharedPreferences>()
    private val sharedPrefEditor = mock<SharedPreferences.Editor>()
    private val sharedPrefEditor2 = mock<SharedPreferences.Editor>()
    private lateinit var repository: ConfigRepository

    @Before
    fun setUp() {
        repository = ConfigRepositoryImpl(sharedPref)
    }

    @Test
    fun shouldReturnInitialDataPopulatedFlag() {
        whenever(sharedPref.getBoolean(INITIAL_DATA_POPULATED, false)).thenReturn(true)
        assertTrue(repository.checkInitialDataPopulated())
    }

    @Test
    fun shouldReturnInitialDataNotPopulatedFlag() {
        whenever(sharedPref.getBoolean(INITIAL_DATA_POPULATED, false)).thenReturn(false)
        assertFalse(repository.checkInitialDataPopulated())
    }

    @Test
    fun shouldInitializeDataPopulatedFlag() {
        whenever(sharedPref.edit()).thenReturn(sharedPrefEditor)
        whenever(sharedPref.edit().putBoolean(INITIAL_DATA_POPULATED, true)).thenReturn(sharedPrefEditor2)
        repository.setInitialDataPopulated()
        verify(sharedPrefEditor, times(1)).putBoolean(INITIAL_DATA_POPULATED, true)
        verify(sharedPrefEditor2, times(1)).apply()
    }

}