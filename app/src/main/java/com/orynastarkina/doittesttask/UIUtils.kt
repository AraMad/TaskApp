package com.orynastarkina.doittesttask

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import com.orynastarkina.doittesttask.base.BaseActivity

/**
 * Created by Oryna Starkina on 18.03.2019.
 */

fun showPreLoader(activity: BaseActivity<*, *>?,
                  descriptionText: String? = null): AlertDialog? {
    if (activity == null) {
        return null
    }

    val binding = DataBindingUtil.inflate<com.orynastarkina.doittesttask.databinding.PreloaderBinding>(
        activity.layoutInflater,
        R.layout.preloader,
        null,
        false)
    binding.description = descriptionText ?: activity
        .getString(R.string.preloader_description_text_default)

    val builder = AlertDialog.Builder(activity)
    builder.setView(binding.root)

    val alert = builder.create()
    alert.setCanceledOnTouchOutside(false)
    alert.setCancelable(true)
    alert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    alert.show()

    return alert
}