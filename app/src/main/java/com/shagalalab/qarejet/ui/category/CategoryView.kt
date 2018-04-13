package com.shagalalab.qarejet.ui.category

import com.shagalalab.qarejet.domain.model.CategoryWithAmount
import com.shagalalab.qarejet.domain.model.Transaction

interface CategoryView {
    fun updateData(data: Pair<List<Transaction>, List<CategoryWithAmount>>)
}