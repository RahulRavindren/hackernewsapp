package com.hackernewsapplication.android.view.detail.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.android.interfaces.ItemDataFetchCallback
import com.hackernewsapplication.common.C
import com.hackernewsapplication.common.basecommons.BaseViewHolder
import com.hackernewsapplication.common.utils.loadAsync
import com.hackernewsapplication.common.utils.toHtml
import kotlinx.android.synthetic.main.comment_viewholder_item.*

/**
 * @Author rahulravindran
 */
class CommentItemViewHolder(
    private val view: View,
    private val callback: ItemDataFetchCallback
) : RecyclerView.ViewHolder(view), BaseViewHolder {
    private var entity: NewsEntity? = null
    var nestedCommentView: TextView? = null
    override val containerView: View?
        get() = view

    override fun onBind(data: Any) {
        if ((data as NewsEntity).id != itemId.toInt()) return

        entity = data

        if (entity?.title?.isEmpty() == true && entity?.type?.isEmpty() == true) {
            callback.onfetchNewsForId(entity?.id ?: -1, this, adapterPosition)
        } else {
            top_level_comment.text = entity?.text?.toHtml()
        }
    }

    fun inflateNestedComments(data: Any) {
        val entity = data as NewsEntity
        // async build nested comments
        loadAsync(view.context, R.layout.nested_comment_viewholder_item, inner_nested_comment_container) {
            nestedCommentView = this.findViewById<TextView>(R.id.inner_comment)
            nestedCommentView?.text = entity.text.toHtml()
        }
    }

    override fun onAttach() {

    }

    override fun onDetach() {
    }

    override fun onDestroy() {
        nestedCommentView?.text = C.EMPTY_STRING
        top_level_comment?.text = C.EMPTY_STRING
    }
}
