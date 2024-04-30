package com.example.composeapp.ui.components.images

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import com.example.composeapp.ui.components.R

@Composable
internal fun placeholderDrawable(context: Context): Drawable? {
    return if (LocalInspectionMode.current) {
        AppCompatResources.getDrawable(context, R.drawable.ic_preview)
    } else {
        null
    }
}
