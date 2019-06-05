package com.hackernewsapplication.common

import androidx.appcompat.app.AppCompatActivity
import com.hackernewsapplication.common.utils.PermissionManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PermissionManagerTest {
    @Mock
    lateinit var activity: AppCompatActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test(expected = NullPointerException::class)
    fun `nulladapterTest`() {
        PermissionManager(null)
    }
}