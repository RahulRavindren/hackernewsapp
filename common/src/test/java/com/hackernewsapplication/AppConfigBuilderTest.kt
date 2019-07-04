package com.hackernewsapplication

import com.hackernewsapplication.common.utils.AppConfigBuilder
import org.junit.Assert
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(JUnit4ClassRunner::class)
class AppConfigBuilderTest {

    @Test(expected = NullPointerException::class)
    fun `test fail for no application id`() {
        val builder = AppConfigBuilder.Builder().apply {
            applicationCode = -1
            applicationId = ""
            applicationVersion = ""
            env = ""
        }
        builder.build()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test fail for application code -1`() {
        val builder = AppConfigBuilder.Builder().apply {
            applicationCode = -1
            applicationId = "com.sample.android"
            applicationVersion = ""
            env = ""
        }
        builder.build()
    }

    @Test
    fun `test check validity of config passed`() {
        val builder = AppConfigBuilder.Builder().apply {
            applicationCode = 1
            applicationId = "com.sample.android"
            applicationVersion = "1.0"
            env = "DEBUG"
        }
        builder.build()

        Assert.assertEquals(1, AppConfigBuilder.getInstance()?.applicationCode)
        Assert.assertEquals("com.sample.android", AppConfigBuilder.getInstance()?.applicationId)
        Assert.assertEquals("1.0", AppConfigBuilder.getInstance()?.applicationVersion)
        Assert.assertEquals("DEBUG", AppConfigBuilder.getInstance()?.env)
    }

}
