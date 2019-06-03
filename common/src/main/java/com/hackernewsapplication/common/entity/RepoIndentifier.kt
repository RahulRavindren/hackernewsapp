package com.hackernewsapplication.common.entity

/**
 * @Author rahulravindran
 */

sealed class BaseIdentifier

data class RepoIndentifier(val key: String, val id: Int = -1) : BaseIdentifier() {
    companion object {
        val TOP_STORIES_ALL = RepoIndentifier("top_stories_all")
    }
}

