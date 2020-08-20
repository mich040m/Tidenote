package tideware.app.tidenote.services

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

class DateService {
     companion object DataService{
        fun Date.agoConverter() : CharSequence {
            val data = DateUtils.getRelativeTimeSpanString(this.time , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS)
            if(data.toString().toLowerCase(Locale.getDefault()) == "yesterday"){
                return "1 day"
            }else{
                return DateUtils.getRelativeTimeSpanString(this.time , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS).dropLast(4);
            }
        }
    }
}