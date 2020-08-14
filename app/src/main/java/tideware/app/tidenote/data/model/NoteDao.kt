package tideware.app.tidenote.data.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY created DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note: Note)
}