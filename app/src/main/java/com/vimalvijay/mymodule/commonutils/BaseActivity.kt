package com.vimalvijay.mymodule.commonutils

import androidx.appcompat.app.AppCompatActivity
import com.vimalvijay.mymodule.network.responsehandler.Resource

open class BaseActivity : AppCompatActivity() {

    open fun <T> getResponse(isFromHeros: String, it: Resource<T>?) {}
}