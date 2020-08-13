package tideware.app.tidenote.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tideware.app.tidenote.R
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.services.FavoriteService

class NoteViewAdapter(private val notes: List<Note>,private val cellClickListener: CellClickListener) : RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder>() {


    class NoteViewHolder(inflater: LayoutInflater,parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
        R.layout.view_note,parent,false)) {
        private var myTitleView : TextView? = itemView.findViewById(R.id.note_title)
        private var myTextView : TextView? = itemView.findViewById(R.id.note_text)
        private var myFavorite : ToggleButton? = itemView.findViewById(R.id.favorite_toggle_button)
        private var myDate: TextView? = itemView.findViewById(R.id.note_date)


        fun bind(note: Note){
            myTitleView?.text = note.title
            myTextView?.text = note.text

            FavoriteService().changeFavorite(itemView.context,note,myFavorite)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note: Note = notes[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(note)
        }
    }

    override fun getItemCount(): Int = notes.count()

}