package com.hackernewsapplication.common.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler


interface Scheduler {
    fun io(): io.reactivex.Scheduler
    fun computation(): io.reactivex.Scheduler
    fun ui(): io.reactivex.Scheduler
    fun newThread(): io.reactivex.Scheduler
}


class SchedulerProvider : Scheduler {
    override fun io(): io.reactivex.Scheduler = Schedulers.io()

    override fun computation(): io.reactivex.Scheduler = Schedulers.computation()

    override fun ui(): io.reactivex.Scheduler = AndroidSchedulers.mainThread()

    override fun newThread(): io.reactivex.Scheduler = Schedulers.single()
}

class TrampolineSchedulerProvider : Scheduler {
    override fun io(): io.reactivex.Scheduler = Schedulers.trampoline()

    override fun computation(): io.reactivex.Scheduler = Schedulers.trampoline()

    override fun ui(): io.reactivex.Scheduler = Schedulers.trampoline()

    override fun newThread(): io.reactivex.Scheduler = Schedulers.trampoline()
}

class TestSchedulerProvider(val scheduler: TestScheduler) : Scheduler {
    override fun io(): io.reactivex.Scheduler = scheduler

    override fun computation(): io.reactivex.Scheduler = scheduler

    override fun ui(): io.reactivex.Scheduler = scheduler

    override fun newThread(): io.reactivex.Scheduler = scheduler
}
