package tideware.app.tidenote.ui.viewmodel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tideware.app.tidenote.data.ApplicationDatabase
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.data.repo.NoteRepository

class MainViewModel  @ViewModelInject constructor(
    val repository: NoteRepository
): ViewModel() {

     var notes: LiveData<List<Note>>

    init {

        notes = repository.getAllNotes()
    }
    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllNotes()
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)
            notes = repository.getAllNotes()
        }
    }




}