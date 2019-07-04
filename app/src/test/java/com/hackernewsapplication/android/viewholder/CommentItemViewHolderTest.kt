package com.hackernewsapplication.android.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.ItemDataFetchCallback
import com.hackernewsapplication.android.view.detail.viewholder.CommentItemViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(RecyclerView.ViewHolder::class)
class CommentItemViewHolderTest {
    @Mock
    lateinit var view: View
    @Mock
    lateinit var callback: ItemDataFetchCallback
    lateinit var commentItemViewHolder: CommentItemViewHolder

    lateinit var mockVH: RecyclerView.ViewHolder


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        commentItemViewHolder = PowerMockito.spy(CommentItemViewHolder(view, callback))

    }

    @Test
    fun `test if callback is called in viewholder`() {
        //setting item id to viewholder
        commentItemViewHolder.onBind(NewsEntity(id = -1))
        Mockito.verify(callback).onfetchNewsForId(-1, commentItemViewHolder, -1)
    }
}
