package com.hackernewsapplication.common

import com.hackernewsapplication.common.utils.PermissionManager
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(JUnit4ClassRunner::class)
class PermissionManagerTest {

    @Test(expected = NullPointerException::class)
    fun `nulladapterTest`() {
        PermissionManager(null)
    }
}