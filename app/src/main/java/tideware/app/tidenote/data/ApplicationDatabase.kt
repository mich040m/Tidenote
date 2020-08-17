package tideware.app.tidenote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.data.model.NoteDao
import tideware.app.tidenote.other.Constants

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun noteDao() : NoteDao

}