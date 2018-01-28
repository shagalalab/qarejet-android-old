package com.shagalalab.qarejet.ui.record

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.Transaction
import com.shagalalab.qarejet.ui.widget.monthview.Month
import com.shagalalab.qarejet.ui.widget.monthview.MonthView
import kotlinx.android.synthetic.main.fragment_records.*
import org.joda.time.DateTime
import javax.inject.Inject

class RecordsFragment : Fragment(), RecordsView {
    private lateinit var transactionAdapter: RecordsAdapter
    @Inject lateinit var presenter: RecordsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as QarejetApp).component.inject(this)
        presenter.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        monthView.init(DateTime.now(), DateTime.now().year().get(), monthListener)

        if (recordsRecyclerView.adapter == null) {
            transactionAdapter = RecordsAdapter()
            recordsRecyclerView.adapter = transactionAdapter
        } else {
            transactionAdapter = recordsRecyclerView.adapter as RecordsAdapter
        }
        recordsRecyclerView.layoutManager = LinearLayoutManager(context)
        recordsRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onResume() {
        super.onResume()
        monthView.setMonth(Month.CURRENT)
    }

    override fun updateTransactions(transactions: List<Transaction>) {
        transactionAdapter.update(transactions)
    }

    private val monthListener = object : MonthView.MonthListener {
        override fun changeMonthText(date: DateTime) {
            presenter.requestData(date)
        }
    }
}