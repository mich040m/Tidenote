package tideware.app.tidenote.data.repo

import androidx.lifecycle.LiveData
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.data.model.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    fun getAllNotes():LiveData<List<Note>>{
        return noteDao.getAllNotes()
    }

}