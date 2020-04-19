package com.boros.android.sample.shared.util

import com.boros.android.sample.R
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

}