package com.hackernewsapplication.android.viewholder

import android.view.View
import com.hackernewsapplication.android.interfaces.ItemDataFetchCallback
import com.hackernewsapplication.android.view.detail.viewholder.CommentItemViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CommentItemViewHolderTest {
    @Mock
    lateinit var view: View
    @Mock
    lateinit var callback: ItemDataFetchCallback

    lateinit var commentItemViewHolder: CommentItemViewHolder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        commentItemViewHolder = CommentItemViewHolder(view, callback)
    }

    @Test
    fun `test bind of viewholder`() {

    }
}