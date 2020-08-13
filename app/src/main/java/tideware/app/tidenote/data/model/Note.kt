package tideware.app.tidenote.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "note_table")
data class Note(@PrimaryKey(autoGenerate = true) val id: Int, var title: String, var text:String, var favorite:Boolean, val created: Date, var lastTimeEdited: Date) : Parcelable