package com.example.test.presentation.utills

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.*


fun Fragment.hasPermissionCheckAndRequest(
    requestPermissionLauncher: ActivityResultLauncher<Array<String>>,
    permission: Array<String>,
): Boolean {
    for (per in permission) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                per
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(permission)
            return false
        }
    }
    return true
}

fun Fragment.showToastShort(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
fun Fragment.showToastShort(@StringRes textFromRes: Int) {
    Toast.makeText(context, textFromRes, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToastLong(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun Fragment.showToastLong(@StringRes textFromRes: Int) {
    Toast.makeText(context, textFromRes, Toast.LENGTH_LONG).show()
}

@SuppressLint("SimpleDateFormat")
fun getCurrentTime():String{
    val dateTime = SimpleDateFormat("MM-dd HH:mm")
    return dateTime.format(Date())
}