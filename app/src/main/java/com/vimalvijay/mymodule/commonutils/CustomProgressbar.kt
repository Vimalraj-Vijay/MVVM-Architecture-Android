package com.vimalvijay.mymodule.commonutils


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.vimalvijay.mymodule.R


class CustomProgressbar {
    var mProgressDialog: Dialog? = null

    fun showProgress(context: Context?) {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
        try {
            mProgressDialog = getDialog(context, R.layout.custom_progress_bar)
            mProgressDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun getDialog(mContext: Context?, mLayout: Int): Dialog? {
        val mDialog = Dialog(mContext!!, R.style.AppTheme)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.window!!.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                    or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )
        mDialog.setContentView(mLayout)
        mDialog.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        mDialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        return mDialog
    }

    fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

}