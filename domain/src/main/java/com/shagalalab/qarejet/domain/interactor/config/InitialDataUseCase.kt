package com.shagalalab.qarejet.domain.interactor.config

import com.shagalalab.qarejet.domain.repository.ConfigRepository

class InitialDataUseCase constructor(
        private val configRepository: ConfigRepository
) {

    fun isDataPopulated() = configRepository.checkInitialDataPopulated()

    fun setInitialDataPopulated() {
        configRepository.setInitialDataPopulated()
    }

}