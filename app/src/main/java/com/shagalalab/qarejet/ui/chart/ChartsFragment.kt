package com.shagalalab.qarejet.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.CategoryWithAmount
import com.shagalalab.qarejet.ui.widget.monthview.Month
import com.shagalalab.qarejet.ui.widget.monthview.MonthView
import com.shagalalab.qarejet.util.Constants
import kotlinx.android.synthetic.main.fragment_charts.*
import org.joda.time.DateTime
import javax.inject.Inject

class ChartsFragment : Fragment(), ChartsView {
    @Inject lateinit var presenter: ChartsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as QarejetApp).component.inject(this)
        presenter.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_charts, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chartsMonthView.init(DateTime.now(), DateTime.now().year().get(), monthListener)
    }

    override fun onResume() {
        super.onResume()
        chartsMonthView.setMonth(Month.CURRENT)
    }

    override fun updateData(categories: List<CategoryWithAmount>) {
        val entries = categories
            .filter { it.type == Constants.TRANSACTION_TYPE_EXPENSE }
            .map { PieEntry(it.amount.toFloat(), it.category.title) }

        val set = PieDataSet(entries, "Expenses")
        set.colors = categories.map { ContextCompat.getColor(activity, it.category.color) }
        set.valueTextColor = Color.WHITE
        set.valueTextSize = 13f
        pieChart.isDrawHoleEnabled = false
        pieChart.transparentCircleRadius = 0f
        pieChart.data = PieData(set)
        pieChart.description.isEnabled = false
//        pieChart.setExtraOffsets(15f, 0f, 0f, 0f)
        pieChart.setDrawEntryLabels(false)
        pieChart.invalidate()

        val legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
    }

    private val monthListener = object : MonthView.MonthListener {
        override fun changeMonthText(date: DateTime) {
            presenter.requestData(date)
        }
    }
}