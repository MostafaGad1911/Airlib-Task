package gad.projects.airlib_task.core.helpers

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getGreetingMessage(): String {
    val sdf = SimpleDateFormat("HH", Locale.ENGLISH)
    val str: String = sdf.format(Date())
    Log.i("CurrentTime", str)
    if (str.toInt() in 1..11) {
        return "Good morning"
    }else if (str.toInt() in 12..17) {
        return "Good after noon"
    }else if (str.toInt() in 17..24) {
        return "Good evening"
    }
    return str
}