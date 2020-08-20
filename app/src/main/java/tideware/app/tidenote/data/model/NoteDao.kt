package tideware.app.tidenote.data.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY lastTimeEdited DESC")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM note_table ORDER BY favorite DESC,lastTimeEdited DESC")
    fun getAllNotesFavoriteOrder() : LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE title LIKE '%' || :searchString || '%' ORDER BY title ASC")
    fun getAllNotesQueryTitle(searchString : String) : LiveData<List<Note>>

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}