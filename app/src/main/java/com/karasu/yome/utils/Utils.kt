package com.karasu.yome.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.net.URL
import java.net.URLConnection

object Utils {

    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    /**
     * Pretty prints an object
     */
    fun pp(obj: Any?): String {
        return gson.toJson(obj)
    }

    fun closeKeyboard(activity: Activity, view: View) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showToast(context: Context?, message: String?) {
        val toast = Toast.makeText(
            context,
            HtmlCompat.fromHtml("<font color='red'><b>$message</b></font>", HtmlCompat.FROM_HTML_MODE_LEGACY),
            Toast.LENGTH_SHORT
        )
        // toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }

    fun testUrl(url: String): Boolean {
        return try {
            val myUrl = URL(url)
            val connection: URLConnection = myUrl.openConnection()
            connection.connectTimeout = 5
            connection.connect()
            true
        } catch (e: Exception) {
            false
        }
    }

}