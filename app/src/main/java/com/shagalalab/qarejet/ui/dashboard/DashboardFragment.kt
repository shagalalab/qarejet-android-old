package com.shagalalab.qarejet.ui.dashboard


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.domain.model.TotalCash
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class DashboardFragment : Fragment(), DashboardView {
    @Inject
    lateinit var presenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as QarejetApp).component.inject(this)
        presenter.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.requestData()
    }


    override fun setCashFlow(totalCash: TotalCash) {
        cash_amount.text = getString(R.string.cash_flow_value).format(totalCash.incomeAmount - totalCash.expenseAmount)
        income_text.text = getString(R.string.income_value).format(totalCash.incomeAmount)
        expense_text.text = getString(R.string.expense_value).format(totalCash.expenseAmount)
    }
}
