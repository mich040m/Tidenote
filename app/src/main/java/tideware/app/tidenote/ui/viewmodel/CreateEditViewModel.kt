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

class CreateEditViewModel @ViewModelInject constructor(
    private val repository: NoteRepository
): ViewModel() {

    init {
    }

    fun insertNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertNote(note)
        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteNote(note)
        }
    }
}