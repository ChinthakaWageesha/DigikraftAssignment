package com.example.digikraftassignment.core.presentation

import android.app.Dialog
import android.content.Intent
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.digikraftassignment.R
import com.example.digikraftassignment.core.extension.showToast
import com.example.digikraftassignment.core.util.ApiResponseCodes
import com.example.digikraftassignment.core.util.Msg
import com.example.digikraftassignment.network.SupportInterceptor
import org.koin.android.ext.android.inject

class BaseActivity: AppCompatActivity() {

    private var progress: Dialog? = null
    private val mNetworkInterceptor by inject<SupportInterceptor>()

    private fun setProgressLoader() {
        progress = Dialog(this, R.style.ProgressbarStyle).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.item_progress_loader)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }
    }

    private fun setNetworkInterceptor() {
        mNetworkInterceptor.setAuthCallBackListener(object :
            SupportInterceptor.AuthenticatorCallBack {

            override fun onUnAuthorizedResponse(responseCode: Int) {
                when (responseCode) {
                    ApiResponseCodes.UNAUTHORIZED -> handleResponse()
                    ApiResponseCodes.SERVER_ERROR -> runOnUiThread {
                        Msg.ERROR_COMMON.showToast(this@BaseActivity)
                    }
                }
            }
        })
    }

    private fun handleResponse() {
        val intent = Intent(this@BaseActivity, CoreActivity::class.java)
        startActivity(intent)
        finishAffinity()

    }

    fun showProgress() {
        progress?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    fun hideProgress() {
        progress?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }
}