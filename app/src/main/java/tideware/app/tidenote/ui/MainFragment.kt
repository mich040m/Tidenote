package tideware.app.tidenote.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import tideware.app.tidenote.R
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.ui.adapter.CellClickListener
import tideware.app.tidenote.ui.adapter.NoteViewAdapter
import tideware.app.tidenote.ui.viewmodel.MainViewModel
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tideware.app.tidenote.services.FavoriteService
import tideware.app.tidenote.ui.adapter.FavoriteClickListener


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class MainFragment : Fragment(), CellClickListener, FavoriteClickListener {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    private fun checkFavoriteIconChange(toolbar: Toolbar) {
        if (!FavoriteService.FavoriteService.check) {
            FavoriteService.FavoriteService.check = true
            toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_checked_star)
        } else if (FavoriteService.FavoriteService.check) {
            FavoriteService.FavoriteService.check = false
            toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_star)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val t:androidx.appcompat.widget.Toolbar  = requireActivity().findViewById(R.id.toolbar)
        
        val adapter = NoteViewAdapter(this,this)
        val recycler = view.note_recycler_view
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(activity)
        if (FavoriteService.FavoriteService.check)
            t.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_checked_star)
        else
            t.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_star)
        val onBackPressedCallback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

                checkFavoriteIconChange(t)
                if (FavoriteService.FavoriteService.check){
                    mainViewModel.favoriteNotes.observe(viewLifecycleOwner) {
                        adapter.setData(it)
                    }
                    mainViewModel.notes.removeObservers(viewLifecycleOwner)

                }else{
                    mainViewModel.notes.observe(viewLifecycleOwner) {
                        adapter.setData(it)
                    }
                    mainViewModel.favoriteNotes.removeObservers(viewLifecycleOwner)

                }
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(viewLifecycleOwner,onBackPressedCallback)


        mainViewModel.notes.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        fab_main.setOnClickListener {
            if(findNavController().currentDestination?.id != R.id.CreateEditFragment){
                findNavController().navigate(R.id.action_MainFragment_to_CreateEditFragment)
            }
        }
    }

    override fun onCellClickListener(note: Note) {
        if(findNavController().currentDestination?.id != R.id.CreateEditFragment){
        val action = MainFragmentDirections.actionMainFragmentToCreateEditFragment(note)
        findNavController().navigate(action)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_delete_all ->{
                deleteAllNotes()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _,_->
            mainViewModel.deleteAllNotes()
            Toast.makeText(requireContext(),"Successfully deleted all notes!",Toast.LENGTH_LONG).show()
        }
        builder.setTitle("Delete Everything")
        builder.setMessage("Are you sure you want to delete all notes?")
        builder.setNegativeButton("No"){ _,_ -> }
        builder.create().show()

    }

    override fun onFavoriteClickListener(
        note: Note
    ) {

            mainViewModel.updateNote(note)





    }

    //listOf(Note("bla","ble",true,Calendar.getInstance().time,Date(2019,7,7,0,7)),Note("test","test",false,Calendar.getInstance().time, Date(2020,7,4,2,6)))

}