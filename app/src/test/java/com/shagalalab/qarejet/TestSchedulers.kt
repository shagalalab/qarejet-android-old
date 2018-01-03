package com.shagalalab.qarejet

import com.shagalalab.qarejet.util.SchedulersProvider
import io.reactivex.schedulers.Schedulers

class TestSchedulers : SchedulersProvider {
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}