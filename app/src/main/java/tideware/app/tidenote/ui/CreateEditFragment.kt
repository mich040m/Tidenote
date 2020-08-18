package tideware.app.tidenote.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_edit.*
import tideware.app.tidenote.R
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.services.DateService.DataService.agoConverter
import tideware.app.tidenote.services.FavoriteService
import tideware.app.tidenote.ui.viewmodel.CreateEditViewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class CreateEditFragment : Fragment() {

    private val createEditViewModel: CreateEditViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)


        return inflater.inflate(R.layout.fragment_create_edit, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = getNoteFromArgs()
        if(note != null)
        {
            favorite_toggle_button_edit.isChecked = note.favorite
            FavoriteService().changeFavorite(
                this.requireContext(),
                note,
                favorite_toggle_button_edit)


            note_title_edit.setText(note.title)
            note_text_edit.setText(note.text)
            note_date_edit.setText(note.lastTimeEdited.agoConverter())


        }

        favorite_toggle_button_edit.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                buttonView.setBackground(ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_checked_star))
            }else{
                buttonView.setBackground(ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_unchecked_star))
            }
        }


        fab_create.setOnClickListener {

            insertNote(note?.id)

            activity?.onBackPressed()
        }



    }

    private fun navigateToMain() {

        findNavController().navigate(R.id.action_CreateEditFragment_to_MainFragment)


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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_delete ->{
                if (getNoteFromArgs() != null){

                    val builder = AlertDialog.Builder(requireContext())
                    builder.setPositiveButton("Yes") { _,_->
                        createEditViewModel.deleteNote(getNoteFromArgs()!!)
                        Toast.makeText(requireContext(),"Successfully deleted note",Toast.LENGTH_LONG).show()
                        navigateToMain()
                    }
                    builder.setTitle("Delete")
                    builder.setMessage("Are you sure you want to delete the note?")
                    builder.setNegativeButton("No"){ _,_ -> }
                    builder.create().show()

                }else{
                    Toast.makeText(requireContext(),"Cannot delete until created",Toast.LENGTH_LONG).show()
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}