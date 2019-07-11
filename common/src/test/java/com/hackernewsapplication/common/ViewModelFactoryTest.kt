package com.hackernewsapplication.common

import com.hackernewsapplication.common.basecommons.BaseRepository
import com.hackernewsapplication.common.viewmodel.ViewModel
import com.hackernewsapplication.common.viewmodel.ViewModelFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @Author rahulravindran
 */

@RunWith(MockitoJUnitRunner::class)
class ViewModelFactoryTest {
    @Mock
    lateinit var baseRepo: BaseRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test_viewmodel_factory_with_annotation`() {
        val dummyObject = DummyClass(baseRepo)
        Assert.assertNotNull(dummyObject.viewModel)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test_viewmodel_factory_without_annotation`() {
        val dummyObject = DummyClass1(baseRepo)
    }


    @ViewModel<DummyViewModel>(DummyViewModel::class)
    class DummyClass(repository: BaseRepository) {
        val viewModel: DummyViewModel = ViewModelFactory.getInstance(repository)
            .onCreate<DummyViewModel>(DummyClass::class)
    }

    class DummyClass1(repository: BaseRepository) {
        val viewModel: DummyViewModel = ViewModelFactory.getInstance(repository)
            .onCreate<DummyViewModel>(DummyClass1::class)
    }


}