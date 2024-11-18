package com.example.testapplication.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity() : AppCompatActivity() {
    var toast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun showToast(msg: String) {
        toast?.cancel()
        toast = Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT)
        toast?.show()
    }
}