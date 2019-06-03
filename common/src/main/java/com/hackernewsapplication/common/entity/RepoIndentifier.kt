package com.hackernewsapplication.common.entity

/**
 * @Author rahulravindran
 */

sealed class BaseIdentifier

data class RepoIndentifier(val key: String) : BaseIdentifier()