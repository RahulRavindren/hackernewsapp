package com.hackernewsapplication.android.view.detail.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hackernewsapplication.android.R
import com.hackernewsapplication.android.entity.NewsEntity
import com.hackernewsapplication.common.basecommons.BaseViewHolder
import com.hackernewsapplication.common.utils.loadAsync
import kotlinx.android.synthetic.main.comment_viewholder_item.*

/**
 * @Author rahulravindran
 */
class CommentItemViewHolder(val view: View) : RecyclerView.ViewHolder(view), BaseViewHolder {

    override val containerView: View?
        get() = view

    override fun onBind(data: Any) {
        val entity = data as NewsEntity
        top_level_comment.text = entity.text

        // async build nested comments
        if (entity.commentsEntity.size > 0) {
            loadAsync(view.context, R.layout.nested_comment_viewholder_item, inner_nested_comment_container) {
                val nestedCommentView = view.findViewById<TextView>(R.id.inner_comment)
                nestedCommentView.text = entity.commentsEntity[0].text
            }
        }
    }

    override fun onAttach() {

    }

    override fun onDetach() {

    }
}