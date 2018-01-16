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
import kotlinx.android.synthetic.main.fragment_records.*
import org.joda.time.DateTime
import javax.inject.Inject

class RecordsFragment : Fragment(), RecordsView {
    private lateinit var transactionAdapter: RecordsAdapter
    private lateinit var months: Array<String>

    @Inject lateinit var presenter: RecordsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as QarejetApp).component.inject(this)
        presenter.init(this, DateTime.now(), DateTime.now().year().get())
        months = resources.getStringArray(R.array.months)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (recordsRecyclerView.adapter == null) {
            transactionAdapter = RecordsAdapter()
            recordsRecyclerView.adapter = transactionAdapter
        } else {
            transactionAdapter = recordsRecyclerView.adapter as RecordsAdapter
        }
        recordsRecyclerView.layoutManager = LinearLayoutManager(context)
        recordsRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        recordsPreviousMonth.setOnClickListener { presenter.requestTransactions(Month.PREVIOUS) }
        recordsNextMonth.setOnClickListener { presenter.requestTransactions(Month.NEXT) }
    }

    override fun onResume() {
        super.onResume()
        presenter.requestTransactions(Month.CURRENT)
    }

    override fun updateTransactions(transactions: List<Transaction>) {
        transactionAdapter.update(transactions)
    }

    override fun changeMonthText(month: Int, year: String) {
        recordsMonthYear.text = months[month - 1].plus(" ").plus(year)
    }
}