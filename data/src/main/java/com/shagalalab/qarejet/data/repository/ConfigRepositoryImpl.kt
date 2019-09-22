package com.shagalalab.qarejet.data.repository

import android.content.SharedPreferences
import com.shagalalab.qarejet.domain.repository.ConfigRepository

class ConfigRepositoryImpl(private var sharedPrefs: SharedPreferences) : ConfigRepository {
    companion object {
        const val INITIAL_DATA_POPULATED = "INITIAL_DATA_POPULATED"
    }

    override fun checkInitialDataPopulated(): Boolean {
        return sharedPrefs.getBoolean(INITIAL_DATA_POPULATED, false)
    }

    override fun setInitialDataPopulated() {
        sharedPrefs.edit().putBoolean(INITIAL_DATA_POPULATED, true).apply()
    }
}