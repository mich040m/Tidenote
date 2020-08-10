package tideware.app.tidenote.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import tideware.app.tidenote.R
import tideware.app.tidenote.model.Note

class NoteViewAdapter(private val notes: List<Note>,private val cellClickListener: CellClickListener) : RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder>() {

    class NoteViewHolder(inflater: LayoutInflater,parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
        R.layout.view_note,parent,false)) {
        private var mTitleView : TextView? = itemView.findViewById(R.id.note_title)
        private var mTextView : TextView? = itemView.findViewById(R.id.note_text)


        fun bind(note: Note){
            mTitleView?.text = note.title
            mTextView?.text = note.text

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
            cellClickListener.onCellClickListener(note.title)
        }
    }

    override fun getItemCount(): Int = notes.count()

}