package com.hackernewsapplication.common.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.hackernewsapplication.common.R
import com.hackernewsapplication.common.utils.Utils


class Image {
    companion object {

        var imageRequestBuilder: RequestBuilder<Bitmap>? = null

        private val DEFAULT_PARAMS = GlideBuilderParams().apply {
            memoryCache = true
            diskCache = true
            errorRetry = 3
        }


        fun init(context: Context) {
            //configure glide for application
            object : AppGlideModule() {
                override fun applyOptions(context: Context, builder: GlideBuilder) {
                    builder.apply {
                        setMemoryCache(LruResourceCache(
                            (DEFAULT_PARAMS.MEMORY_POOL_SIZE *
                                    1024 * 1024).toLong()
                        ).apply {
                            setResourceRemovedListener(CacheListeners.memoryCacheListener)
                        })

                        setBitmapPool(LruBitmapPool((DEFAULT_PARAMS.MEMORY_POOL_SIZE * 1024 * 1024).toLong()))

                    }
                }
            }
        }

//        fun recylerPreLoadImageListener(fragment: BaseFragment): RecyclerViewPreloader<Any> {
//            val preloader = FixedPreloadSizeProvider<Any>(Utils.getDeviceWidth(), 140.toDp())
//            val modelprovider = object : ListPreloader.PreloadModelProvider<Any> {
//                override fun getPreloadItems(position: Int): MutableList<Any> {
//                    return mutableListOf()
//                }
//
//                override fun getPreloadRequestBuilder(item: Any): RequestBuilder<*>? {
//
//                }
//            }
//            return RecyclerViewPreloader<Any>(fragment, modelprovider , preloader, 10)
//        }

        fun load(view: ImageView, parameters: ImageParams? = null, url: String) {
            Glide.with(view.context)
                .load(url)
                .override(
                    parameters?.width ?: Utils.getDeviceWidth(), parameters?.height
                        ?: Utils.getDeviceHeight()
                )
                .centerCrop()
                .placeholder(parameters?.placeholder)
                .into(view)
        }

        fun load(view: ImageView, url: String) {
            load(
                view,
                ImageParams().apply {
                    width = view.width
                    height = view.height
                    placeholder = Utils.application?.getDrawable(R.drawable.default_image_placeholder)
                },
                url
            )
        }

    }


}

class GlideBuilderParams {
    val MEMORY_POOL_SIZE = 30 // as number
    val DISK_CACHE_SIZE = 100
    val DISK_LOCATION_URI = ""
    val DEFAULT_RETRY_TIME = 3

    var memoryCache: Boolean = true
    var diskCache: Boolean = true
    var errorRetry = DEFAULT_RETRY_TIME

}

class ImageParams {
    lateinit var transform: () -> Unit
    var width: Int = -1
    var height: Int = -1
    var error: Drawable? = null
    var placeholder: Drawable? = null
}
