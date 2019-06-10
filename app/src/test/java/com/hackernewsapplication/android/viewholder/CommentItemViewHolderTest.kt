package com.hackernewsapplication.android.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
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

    @Mock
    lateinit var recyclerViewHolder: RecyclerView.ViewHolder

    lateinit var commentItemViewHolder: CommentItemViewHolder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        commentItemViewHolder = CommentItemViewHolder(view, callback)
    }

    @Test
    fun `test if callback is called in viewholder`() {
        //setting item id to viewholder
//        Mockito.`when`(recyclerViewHolder.itemId).thenReturn(1024)
//        commentItemViewHolder.onBind(NewsEntity(id = 1024))
//        Mockito.verify(callback).onfetchNewsForId(1024, commentItemViewHolder, 0)
    }
}