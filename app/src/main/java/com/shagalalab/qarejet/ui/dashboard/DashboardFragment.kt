package com.shagalalab.qarejet.ui.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.TotalCash
import com.shagalalab.qarejet.ui.record.RecordsAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class DashboardFragment : Fragment(), DashboardView {
    @Inject
    lateinit var presenter: DashboardPresenter
    private lateinit var lastTransactionsAdapter: RecordsAdapter
    private lateinit var accountsAdapter: AccountsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as QarejetApp).component.inject(this)
        presenter.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountsAdapter = AccountsAdapter()
        accountList.adapter = accountsAdapter
        accountList.layoutManager = LinearLayoutManager(context)
        accountList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        lastTransactionsAdapter = RecordsAdapter()
        lastTransactionsRecyclerView.adapter = lastTransactionsAdapter
        lastTransactionsRecyclerView.layoutManager = LinearLayoutManager(context)
        lastTransactionsRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onResume() {
        super.onResume()
        presenter.requestData()
    }

    override fun setCashFlow(totalCash: TotalCash) {
        val leftAmount: Double = totalCash.incomeAmount - totalCash.expenseAmount
        cashAmount.text = getString(R.string.cash_flow_value).format(leftAmount)
        incomeText.text = getString(R.string.income_value).format(totalCash.incomeAmount)
        expenseText.text = getString(R.string.expense_value).format(totalCash.expenseAmount)
        totalBudget.text = getString(R.string.total_budget_value).format(totalCash.incomeAmount)
        progressText.text = getString(R.string.progress_value).format(totalCash.incomeAmount, leftAmount)
        budgetProgress.max = totalCash.incomeAmount.toInt()
        budgetProgress.progress = totalCash.expenseAmount.toInt()
        accountsAdapter.updateData(totalCash.accountList)
        lastTransactionsAdapter.update(totalCash.lastTransactions)
    }
}
