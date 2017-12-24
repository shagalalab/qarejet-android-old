package com.shagalalab.qarejet.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.shagalalab.qarejet.QarejetApp
import com.shagalalab.qarejet.R
import com.shagalalab.qarejet.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashView {
    @Inject lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QarejetApp).component.inject(this)

        presenter.init(this)
        val accounts = resources.getStringArray(R.array.accounts)
        val categories = resources.getStringArray(R.array.categories)
        presenter.checkDataPopulated(accounts, categories)
    }

    override fun goToNextScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, LENGTH_LONG).show()
    }
}