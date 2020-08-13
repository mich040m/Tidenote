package tideware.app.tidenote.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_create_edit.*
import tideware.app.tidenote.R
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.services.FavoriteService
import tideware.app.tidenote.ui.viewmodel.CreateEditViewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateEditFragment : Fragment() {

    private lateinit var createEditViewModel: CreateEditViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        createEditViewModel = ViewModelProvider(this).get(CreateEditViewModel::class.java)
        return inflater.inflate(R.layout.fragment_create_edit, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = getNoteFromArgs()


        favorite_toggle_button_edit.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                buttonView.setBackground(ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_checked_star))
            }else{
                buttonView.setBackground(ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_unchecked_star))
            }
        }
        if(note != null)
        {
            FavoriteService().changeFavorite(this.requireContext(),note,favorite_toggle_button_edit)
            note_title_edit.setText(note.title)
            note_text_edit.setText(note.text)


        }

        fab_create.setOnClickListener {
            insertNote(note?.id)
            findNavController().navigate(R.id.action_CreateEditFragment_to_MainFragment)
        }



    }

    private fun getNoteFromArgs(): Note? {

        val args = arguments?.let { CreateEditFragmentArgs.fromBundle(it) }
        if (args?.note != null) {
            return args.note
        }else{
            return null
        }
    }

    private fun insertNote(id:Int?) {
        createEditViewModel.insertNote(bindToNote(id))
    }

    private fun bindToNote(id: Int?):Note {
        var noteId = 0
        if (id != null)
            noteId = id
        val title = note_title_edit.text.toString()
        val text = note_text_edit.text.toString()
        val favorite = favorite_toggle_button_edit.isChecked

        return Note(noteId,title,text,favorite,Calendar.getInstance().time,Calendar.getInstance().time)
    }
}