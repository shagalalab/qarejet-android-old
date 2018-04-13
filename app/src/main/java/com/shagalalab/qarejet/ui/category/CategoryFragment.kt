package com.shagalalab.qarejet.ui.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.CategoryWithAmount
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.ui.widget.month.MonthListener
import kotlinx.android.synthetic.main.fragment_category.*
import org.joda.time.DateTime
import javax.inject.Inject

class CategoryFragment : Fragment(), CategoryView {
    @Inject lateinit var presenter: CategoryPresenter
    private lateinit var date: DateTime
    private var categoryId = 0L
    private val adapter = CategoryAdapter()
    private val chartMonthSize = 8

    companion object {
        fun newInstance(data: Pair<Long, DateTime>): CategoryFragment {
            val fragment = CategoryFragment()
            val args = Bundle()
            args.putSerializable("data", data)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = arguments.getSerializable("data") as Pair<Long, DateTime>
        categoryId = data.first
        date = data.second
        (activity.application as QarejetApp).component.inject(this)
        presenter.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryMonthView.init(date, monthListener)

        categoryList.layoutManager = LinearLayoutManager(context)
        categoryList.adapter = adapter
        categoryList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        categoryChart.legend.isEnabled = false
        categoryChart.xAxis.setDrawGridLines(false)
        categoryChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        categoryChart.axisRight.isEnabled = false
        categoryChart.extraLeftOffset = 10f
        categoryChart.extraRightOffset = 10f

        val leftAxis = categoryChart.axisLeft
        leftAxis.granularity = 1f
    }

    override fun onResume() {
        super.onResume()
        categoryMonthView.setCurrentMonth()
    }

    override fun updateData(data: Pair<List<Transaction>, List<CategoryWithAmount>>) {
        adapter.updateData(data.first)
        updateChart(data.second)
    }

    private fun updateChart(categories: List<CategoryWithAmount>) {
        val categoriesMap = HashMap<Int, Float>()
        for (c in categories) {
            val monthIndex = DateTime(c.date).monthOfYear
            categoriesMap[monthIndex] = c.amount.toFloat()
        }
        val entries = ArrayList<Entry>()

        // prepare month as axis title
        val monthsArray = resources.getStringArray(R.array.months)
        val monthsList: MutableList<String> = ArrayList(chartMonthSize)
        val currentDate = categoryMonthView.getCurrentDate()
        val startDate = currentDate.minusMonths(chartMonthSize)
        for (i in 1..chartMonthSize) {
            val monthDateTime = startDate.plusMonths(i)
            var shortenedMonth = monthsArray[monthDateTime.monthOfYear - 1].substring(0..2)
            if (i == 1) {
                shortenedMonth += " " + monthDateTime.year.toString()
            }
            monthsList.add(shortenedMonth)

            val amount = if (categoriesMap.containsKey(monthDateTime.monthOfYear)) {
                categoriesMap[monthDateTime.monthOfYear]!!
            } else {
                0f
            }
            entries.add(Entry(i.toFloat() - 1, amount))
        }
        val formatter = IAxisValueFormatter { value, _ -> monthsList[value.toInt()] }
        categoryChart.xAxis.valueFormatter = formatter

        val set1 = LineDataSet(entries, "")
        set1.setCircleColor(ContextCompat.getColor(activity, R.color.color_primary))
        set1.setDrawCircles(true)
        set1.setDrawValues(false)
        set1.circleHoleRadius = 12f
        set1.color = ContextCompat.getColor(activity, R.color.color_primary)
        set1.lineWidth = 2f

        categoryChart.data = LineData(set1)
        categoryChart.description.isEnabled = false
        categoryChart.invalidate()
    }

    private val monthListener = object : MonthListener {
        override fun onMonthChanged(date: DateTime) {
            presenter.requestData(date, categoryId)
        }
    }
}