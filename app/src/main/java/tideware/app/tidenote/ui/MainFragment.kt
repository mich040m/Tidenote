package tideware.app.tidenote.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import tideware.app.tidenote.R
import tideware.app.tidenote.model.Note
import tideware.app.tidenote.ui.adapter.CellClickListener
import tideware.app.tidenote.ui.adapter.NoteViewAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(), CellClickListener {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fab_main.setOnClickListener{
            findNavController().navigate(R.id.action_MainFragment_to_CreateEditFragment)
        }



        note_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NoteViewAdapter(listOf(Note("bla","ble",true,Calendar.getInstance().time,Date(2019,7,7,0,7)),Note("test","test",false,Calendar.getInstance().time, Date(2020,7,4,2,6))),this@MainFragment)
        }
    }

    override fun onCellClickListener(note: Note) {
        val action = MainFragmentDirections.actionMainFragmentToCreateEditFragment(note)
        findNavController().navigate(action)

    }




}