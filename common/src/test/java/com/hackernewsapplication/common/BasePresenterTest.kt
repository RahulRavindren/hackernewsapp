package com.hackernewsapplication.common

import com.hackernewsapplication.common.basecommons.BasePresenter
import com.hackernewsapplication.common.interfaces.BaseView
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BasePresenterTest {

    @Mock
    private lateinit var view: BaseView

    @Test(expected = NullPointerException::class)
    fun `viewNUllTest`() {
        val presenter =
            BasePresenter<BaseView>()
        presenter.attachView(null)
    }

}
