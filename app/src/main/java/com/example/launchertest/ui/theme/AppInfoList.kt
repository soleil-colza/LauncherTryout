package com.example.launchertest.ui.theme

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.launchertest.R
import com.example.launchertest.domain.model.AppInfo

fun create(context: Context): List<AppInfo> {
    val pm = context.packageManager
    val intent = Intent(Intent.ACTION_MAIN)
        .also { it.addCategory(Intent.CATEGORY_LAUNCHER) }
    return pm.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        .asSequence()
        .mapNotNull { it.activityInfo }
        .filter { it.packageName != context.packageName }
        .map {
            AppInfo(
                it.loadIcon(pm) ?: getDefaultIcon(context),
                it.loadLabel(pm).toString(),
                ComponentName(it.packageName, it.name)
            )
        }
        .sortedBy { it.label }
        .toList()
}


fun getDefaultIcon(context: Context): Drawable {
    return ContextCompat.getDrawable(context, R.drawable.ic_default_icon)
        ?: throw IllegalArgumentException("Default icon resource not found")
}
