package tideware.app.tidenote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(var title: String,var text:String) : Parcelable {
}