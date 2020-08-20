package tideware.app.tidenote.data.repo

import androidx.lifecycle.LiveData
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.data.model.NoteDao
import javax.inject.Inject
import javax.inject.Singleton

class NoteRepository @Inject constructor (private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    fun getAllNotes():LiveData<List<Note>>{
        return noteDao.getAllNotes()
    }

    fun getAllNotesFavoriteOrder():LiveData<List<Note>>{
        return noteDao.getAllNotesFavoriteOrder()
    }
    fun getAllNotesQueryTitle(searchString : String) : LiveData<List<Note>> {
        return noteDao.getAllNotesQueryTitle(searchString)
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