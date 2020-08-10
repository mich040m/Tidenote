package tideware.app.tidenote.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_create_edit.*
import tideware.app.tidenote.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateEditFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_create.setOnClickListener {
            findNavController().navigate(R.id.action_CreateEditFragment_to_MainFragment)
        }

        val args   = arguments?.let { CreateEditFragmentArgs.fromBundle(it) }

        note_title_edit.setText(args?.note?.title)
        note_text_edit.setText(args?.note?.text)
    }
}