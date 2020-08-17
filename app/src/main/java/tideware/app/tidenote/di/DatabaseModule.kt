package tideware.app.tidenote.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import tideware.app.tidenote.data.ApplicationDatabase
import tideware.app.tidenote.data.model.NoteDao
import tideware.app.tidenote.other.Constants
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) : ApplicationDatabase{
        return Room.databaseBuilder(appContext,ApplicationDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(database: ApplicationDatabase): NoteDao {
        return database.noteDao()
    }
}