package tideware.app.tidenote.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import tideware.app.tidenote.R
import tideware.app.tidenote.model.Note
import tideware.app.tidenote.ui.adapter.NoteViewAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

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
            adapter = NoteViewAdapter(listOf(Note("bla","ble"),Note("test","test")))
        }
    }
}