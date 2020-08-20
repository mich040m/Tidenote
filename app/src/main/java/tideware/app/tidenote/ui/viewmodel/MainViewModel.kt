package tideware.app.tidenote.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.data.repo.NoteRepository
import tideware.app.tidenote.other.NoteSortType


class MainViewModel  @ViewModelInject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private val searchString = MutableLiveData<String>("")
    private var sortType = NoteSortType.LAST_TIME_EDITED

    private val notes: LiveData<List<Note>> = repository.getAllNotes()
    private val noteFavorite: LiveData<List<Note>> = repository.getAllNotesFavoriteOrder()
    private val noteSearched = Transformations.switchMap(searchString){ input ->
        repository.getAllNotesQueryTitle(searchString.value!!)
    }
    val notesMediator = MediatorLiveData<List<Note>>()

    init {
        notesMediator.addSource(notes) {
            if(sortType == NoteSortType.LAST_TIME_EDITED){
                notesMediator.value = it
            }
        }
        notesMediator.addSource(noteFavorite){
            if(sortType == NoteSortType.FAVORITE){
                notesMediator.value = it
            }
        }
       notesMediator.addSource(noteSearched){
            if(sortType == NoteSortType.SEARCH){
                notesMediator.value = it
            }
        }

    }
    fun searchNotes(searchString : String){
        sortType = NoteSortType.SEARCH
        this.searchString.value = searchString
    }
    fun sortNotes(noteSortType: NoteSortType) = when(noteSortType){
        NoteSortType.LAST_TIME_EDITED -> notes.value?.let { notesMediator.value = it }
        NoteSortType.FAVORITE -> noteFavorite.value?.let { notesMediator.value = it }
        NoteSortType.SEARCH -> noteSearched.value?.let { notesMediator.value = it}
    }.also {
        this.sortType = noteSortType
    }

    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllNotes()
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)

        }
    }
}