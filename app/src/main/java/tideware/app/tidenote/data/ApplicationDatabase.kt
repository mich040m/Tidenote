package tideware.app.tidenote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.data.model.NoteDao

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase{
            synchronized(this){
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java,
                        "application_database"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}