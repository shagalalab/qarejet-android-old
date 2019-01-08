package com.shagalalab.qarejet.ui.dashboard

import com.shagalalab.qarejet.domain.model.TotalCash

interface DashboardView {

    fun setCashFlow(totalCash: TotalCash)

}