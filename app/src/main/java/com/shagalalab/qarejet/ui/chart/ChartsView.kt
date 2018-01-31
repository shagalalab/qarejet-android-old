package com.shagalalab.qarejet.ui.chart

import com.shagalalab.qarejet.domain.model.CategoryWithAmount

interface ChartsView {
    fun updateData(categories: List<CategoryWithAmount>)
}