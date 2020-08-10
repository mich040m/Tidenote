package tideware.app.tidenote.services

import java.text.SimpleDateFormat
import java.util.*

class DateService {

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
}