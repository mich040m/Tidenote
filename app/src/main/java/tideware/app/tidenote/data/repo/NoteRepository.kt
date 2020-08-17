package tideware.app.tidenote.data.repo

import androidx.lifecycle.LiveData
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.data.model.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor (val noteDao: NoteDao) {



    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    fun getAllNotes():LiveData<List<Note>>{
        return noteDao.getAllNotes()
    }
    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

}