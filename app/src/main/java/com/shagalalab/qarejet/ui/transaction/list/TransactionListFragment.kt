package com.shagalalab.qarejet.ui.transaction.list

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
import kotlinx.android.synthetic.main.fragment_transactions.*
import javax.inject.Inject

class TransactionListFragment : Fragment(), TransactionListView {
    private lateinit var transactionAdapter: TransactionListAdapter

    @Inject lateinit var presenter: TransactionListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as QarejetApp).component.inject(this)
        presenter.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.requestAllTransactions()

        if (transactionRecyclerView.adapter == null) {
            transactionAdapter = TransactionListAdapter()
            transactionRecyclerView.adapter = transactionAdapter
        } else {
            transactionAdapter = transactionRecyclerView.adapter as TransactionListAdapter
        }
        transactionRecyclerView.layoutManager = LinearLayoutManager(context)
        transactionRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun updateTransactions(transactions: List<Transaction>) {
        transactionAdapter.update(transactions)
    }
}