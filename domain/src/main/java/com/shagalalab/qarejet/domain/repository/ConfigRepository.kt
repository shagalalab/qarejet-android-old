package com.shagalalab.qarejet.domain.repository

interface ConfigRepository {

    fun checkInitialDataPopulated(): Boolean
    fun setInitialDataPopulated()

}