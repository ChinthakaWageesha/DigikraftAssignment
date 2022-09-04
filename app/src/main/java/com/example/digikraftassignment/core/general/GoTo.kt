package com.example.digikraftassignment.core.general

import android.content.Context
import android.content.Intent
import com.example.digikraftassignment.app.presentation.MainActivity

object GoTo {

    fun main(context: Context) {
        context.startActivity(
            Intent(context, MainActivity::class.java)
        )
    }

}