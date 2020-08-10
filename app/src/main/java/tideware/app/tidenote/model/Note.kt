package tideware.app.tidenote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(var title: String,var text:String,var favorite:Boolean,val created: Date ,var lastTimeEdited: Date) : Parcelable {
}