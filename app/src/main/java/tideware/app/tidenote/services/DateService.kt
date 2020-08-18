package tideware.app.tidenote.services

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

class DateService {



    object DataService{
        fun Date.agoConverter() : CharSequence {
            return DateUtils.getRelativeTimeSpanString(this.time , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS).dropLast(4);
        }
    }
}