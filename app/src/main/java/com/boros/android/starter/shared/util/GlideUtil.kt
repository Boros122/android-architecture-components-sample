package com.boros.android.starter.shared.util

import com.boros.android.starter.R
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object GlideUtil {

    fun basicRequestOption(): RequestOptions {
        return RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_android)
                .error(R.drawable.ic_android)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
    }

    fun circleRequestOption(): RequestOptions {
        return RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_android)
                .error(R.drawable.ic_android)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .circleCrop()
    }

    /*
    Glide.with(this)
                .load("")
                .apply(GlideUtil.basicRequestOption())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, mode: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(imageView)
     */

}