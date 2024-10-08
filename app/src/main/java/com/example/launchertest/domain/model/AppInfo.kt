package com.example.launchertest.domain.model

import android.content.ComponentName
import android.graphics.drawable.Drawable

data class AppInfo(
    val icon: Drawable,
    val label: String,
    val componentName: ComponentName
)
