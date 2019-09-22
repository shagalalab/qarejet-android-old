package com.shagalalab.qarejet.ui.category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Category
import com.shagalalab.qarejet.domain.model.CategoryWithAmount
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.ui.record.RecordsAdapter
import com.shagalalab.qarejet.ui.widget.month.MonthListener
import kotlinx.android.synthetic.main.activity_category.*
import org.joda.time.DateTime
import javax.inject.Inject

class CategoryActivity : AppCompatActivity(), CategoryView {
    @Inject lateinit var presenter: CategoryPresenter
    private lateinit var date: DateTime
    private var categoryId = 0L
    private val adapter = RecordsAdapter()
    private val chartMonthSize = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        (application as QarejetApp).component.inject(this)

        val data = intent?.extras?.getSerializable("data") as Pair<Category, DateTime>
        categoryId = data.first.id
        date = data.second
        presenter.init(this)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = data.first.title

        categoryMonthView.init(date, monthListener)

        categoryList.layoutManager = LinearLayoutManager(this)
        categoryList.adapter = adapter
        categoryList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun updateData(data: Pair<List<Transaction>, List<CategoryWithAmount>>) {
        adapter.update(data.first)

        val categoriesMap = HashMap<Int, Float>()
        for (c in data.second) {
            val monthIndex = DateTime(c.date).monthOfYear
            categoriesMap[monthIndex] = c.amount.toFloat()
        }

        var currentMonthIndex = -1
        var currentColor = R.color.color_primary
        var currency = ""
        if (!data.first.isEmpty()) {
            currentMonthIndex = DateTime(data.first.first().date).monthOfYear
            currentColor = resources.getIdentifier(data.first.first().category.color, "color", packageName)
            currency = data.first.first().account.currency
        }

        if (currentMonthIndex == -1) {
            categoryTotalBalance.text = "0"
        } else {
            categoryTotalBalance.text = categoriesMap[currentMonthIndex].toString().plus(" ").plus(currency)
        }

        updateChart(categoriesMap, currentColor)
    }

    private fun updateChart(categoriesMap: HashMap<Int, Float>, chartLineColor: Int) {
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
        set1.setCircleColor(ContextCompat.getColor(this, chartLineColor))
        set1.setDrawCircles(true)
        set1.setDrawValues(false)
        set1.circleHoleRadius = 12f
        set1.color = ContextCompat.getColor(this, chartLineColor)
        set1.lineWidth = 2f

        categoryChart.data = LineData(set1)
        categoryChart.description.isEnabled = false
        categoryChart.invalidate()
    }

    private fun updateMonthText(date: DateTime) {
        categoryTotalYear.text = date.year.toString()
        categoryTotalMonth.text = date.monthOfYear.toString()
    }

    private val monthListener = object : MonthListener {
        override fun onMonthChanged(date: DateTime) {
            presenter.requestData(date, categoryId)
            updateMonthText(date)
        }
    }
}