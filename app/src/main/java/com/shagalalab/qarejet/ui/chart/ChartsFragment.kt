package com.shagalalab.qarejet.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
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
import com.shagalalab.qarejet.ui.widget.month.MonthListener
import com.shagalalab.qarejet.util.Constants
import kotlinx.android.synthetic.main.fragment_charts.*
import org.joda.time.DateTime
import javax.inject.Inject

class ChartsFragment : Fragment(), ChartsView {
    @Inject lateinit var presenter: ChartsPresenter
    private var transactionType = Constants.TRANSACTION_TYPE_EXPENSE

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
        chartsMonthView.init(DateTime.now(), monthListener)

        chartsTabLayout.getTabAt(Constants.TRANSACTION_TYPE_EXPENSE)?.select()
        chartsTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                transactionType = tab!!.position
                chartsMonthView.setCurrentMonth()
            }
        })

        val legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)

        pieChart.isDrawHoleEnabled = false
        pieChart.transparentCircleRadius = 0f
        pieChart.description.isEnabled = false
//        pieChart.setExtraOffsets(15f, 0f, 0f, 0f)
        pieChart.setDrawEntryLabels(false)
    }

    override fun onResume() {
        super.onResume()
        chartsMonthView.setCurrentMonth()
    }

    override fun updateData(categories: List<CategoryWithAmount>) {
        val entries = categories
            .map { PieEntry(it.amount.toFloat(), it.category.title) }

        val set = PieDataSet(entries, "")
        set.colors = categories.map { ContextCompat.getColor(activity, it.category.color) }
        set.valueTextColor = Color.WHITE
        set.valueTextSize = 13f
        pieChart.data = PieData(set)
        pieChart.animateY(1000)
        pieChart.invalidate()
    }

    private val monthListener = object : MonthListener {
        override fun onMonthChanged(date: DateTime) {
            presenter.requestData(date, transactionType)
        }
    }
}