package com.shagalalab.qarejet.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.core.widgets.month.MonthListener
import com.shagalalab.qarejet.domain.model.Transaction
import kotlinx.android.synthetic.main.fragment_records.*
import org.joda.time.DateTime
import javax.inject.Inject

class RecordsFragment : Fragment(), RecordsView {
    private lateinit var transactionAdapter: RecordsAdapter
    @Inject lateinit var presenter: RecordsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as QarejetApp).component.inject(this)
        presenter.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recordsMonthView.init(DateTime.now(), monthListener)

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
        recordsMonthView.setCurrentMonth()
    }

    override fun updateTransactions(transactions: List<Transaction>) {
        transactionAdapter.update(transactions)
    }

    private val monthListener = object : MonthListener {
        override fun onMonthChanged(date: DateTime) {
            presenter.requestData(date)
        }
    }
}