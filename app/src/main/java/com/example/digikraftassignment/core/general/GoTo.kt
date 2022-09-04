package com.example.digikraftassignment.core.general

import android.content.Context
import android.content.Intent
import com.example.digikraftassignment.app.presentation.detail_viewer.DetailsViewerActivity

object GoTo {

    fun detailsViewer(context: Context) {
        context.startActivity(
            Intent(context, DetailsViewerActivity::class.java)
        )
    }

}