package com.hackernewsapplication.common.basecommons

import kotlinx.android.extensions.LayoutContainer


/**
 * @Author rahulravindran
 */
interface BaseViewHolder : LayoutContainer {
    fun onBind(data: Any)
    fun onAttach()
    fun onDetach()
}