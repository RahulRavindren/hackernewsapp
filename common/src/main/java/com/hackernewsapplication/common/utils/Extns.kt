package com.hackernewsapplication.common.utils

import android.app.Activity
import android.content.Context
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.Html
import android.text.Spanned
import android.util.Size
import android.util.SizeF
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.Serializable


fun bundleOf(vararg pairs: Pair<String, Any?>) = Bundle(pairs.size).apply {
    for ((key, value) in pairs) {
        when (value) {
            null -> putString(key, null) // Any nullable type will suffice.

            // Scalars
            is Boolean -> putBoolean(key, value)
            is Byte -> putByte(key, value)
            is Char -> putChar(key, value)
            is Double -> putDouble(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Short -> putShort(key, value)

            // References
            is Bundle -> putBundle(key, value)
            is CharSequence -> putCharSequence(key, value)
            is Parcelable -> putParcelable(key, value)

            // Scalar arrays
            is BooleanArray -> putBooleanArray(key, value)
            is ByteArray -> putByteArray(key, value)
            is CharArray -> putCharArray(key, value)
            is DoubleArray -> putDoubleArray(key, value)
            is FloatArray -> putFloatArray(key, value)
            is IntArray -> putIntArray(key, value)
            is LongArray -> putLongArray(key, value)
            is ShortArray -> putShortArray(key, value)

            // Reference arrays
            is Array<*> -> {
                val componentType = value::class.java.componentType
                @Suppress("UNCHECKED_CAST") // Checked by reflection.
                when {
                    Parcelable::class.java.isAssignableFrom(componentType) -> {
                        putParcelableArray(key, value as Array<Parcelable>)
                    }
                    String::class.java.isAssignableFrom(componentType) -> {
                        putStringArray(key, value as Array<String>)
                    }
                    CharSequence::class.java.isAssignableFrom(componentType) -> {
                        putCharSequenceArray(key, value as Array<CharSequence>)
                    }
                    Serializable::class.java.isAssignableFrom(componentType) -> {
                        putSerializable(key, value)
                    }
                    else -> {
                        val valueType = componentType.canonicalName
                        throw IllegalArgumentException(
                            "Illegal value array type $valueType for key \"$key\""
                        )
                    }
                }
            }

            // Last resort. Also we must check this after Array<*> as all arrays are serializable.
            is Serializable -> putSerializable(key, value)

            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && value is Binder) {
                    putBinder(key, value)
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && value is Size) {
                    putSize(key, value)
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && value is SizeF) {
                    putSizeF(key, value)
                } else {
                    val valueType = value.javaClass.canonicalName
                    throw IllegalArgumentException("Illegal value type $valueType for key \"$key\"")
                }
            }
        }
    }
}


fun <T> Single<T>.subscribeOnUIThread(observer: SingleObserver<T>) =
    observeOn(AndroidSchedulers.mainThread()).subscribe(observer)


fun Activity.runOnUIThread(runnable: () -> Unit) = runOnUiThread(runnable)

fun Int.toPx(): Int = (this * Utils.application?.resources?.displayMetrics?.density!!).toInt()

fun Int.toDp(): Int = (this / Utils.application?.resources?.displayMetrics?.density!!).toInt()

fun LayoutInflater.inflate(layoutId: Int, viewGroup: ViewGroup? = null, parent: Boolean = false): View {
    return inflate(layoutId, viewGroup, parent)
}


fun loadAsync(context: Context, @LayoutRes res: Int, parent: ViewGroup, action: View.() -> Unit) {
    AsyncLayoutInflater(context).inflate(res, parent) { view, _, parent ->
        with(parent) {
            this?.addView(view)
            action(view)
            this?.invalidate()
        }

    }
}

fun String.toHtml(): Spanned = Html.fromHtml(this)


inline fun <T, reified R> List<T>.mapToTypedArray(transform: (T) -> R): Array<R> {
    return when (this) {
        is RandomAccess -> Array(size) { index -> transform(this[index]) }
        else -> with(iterator()) { Array(size) { transform(next()) } }
    }
}

fun View.showUserFeedback(message: String) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
    } else {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }
}
