package tideware.app.tidenote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import tideware.app.tidenote.R
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.services.FavoriteService

class NoteViewAdapter(private val cellClickListener: CellClickListener,private val favoriteClickListener: FavoriteClickListener) : RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder>() {
    private var notes = emptyList<Note>()


    class NoteViewHolder(inflater: LayoutInflater,parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
        R.layout.view_note,parent,false)) {
        private var myTitleView : TextView? = itemView.findViewById(R.id.note_title)
        private var myTextView : TextView? = itemView.findViewById(R.id.note_text)
        var myFavorite : ToggleButton? = itemView.findViewById(R.id.favorite_toggle_button)
        private var myDate: TextView? = itemView.findViewById(R.id.note_date)


        fun bind(note: Note){
            myTitleView?.text = note.title
            myTextView?.text = note.text


         /*   if (note.favorite){
                myFavorite?.setBackground(ContextCompat.getDrawable(itemView.context, R.drawable.ic_checked_star))
                myFavorite?.isChecked = true
            }else{
                myFavorite?.setBackground(ContextCompat.getDrawable(itemView.context, R.drawable.ic_unchecked_star))
                myFavorite?.isChecked = false
            }*/


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
        FavoriteService().changeFavorite(holder.itemView.context,note,holder.myFavorite,favoriteClickListener)

        holder.myFavorite?.setOnCheckedChangeListener(null)
        if (note.favorite){
            holder.myFavorite?.setBackground(ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_checked_star))
        }else{
            holder.myFavorite?.setBackground(ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_unchecked_star))
        }
        holder.myFavorite?.isChecked = note.favorite
        holder.myFavorite?.setOnCheckedChangeListener{ btn, isChecked ->

            if (isChecked){
                holder.myFavorite?.setBackground(ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_checked_star))
                note.favorite = true
                favoriteClickListener.onFavoriteClickListener(note)
            }else{
                holder.myFavorite?.setBackground(ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_unchecked_star))
                note.favorite = false
                favoriteClickListener.onFavoriteClickListener(note)
            }

        }
       /* holder.myFavorite?.setOnCheckedChangeListener{_,isChecked ->
            favoriteClickListener.onFavoriteClickListener(note,isChecked, holder.myFavorite!!,holder.itemView.context)

        }*/
    }

    override fun getItemCount(): Int = notes.count()

    fun setData(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

}