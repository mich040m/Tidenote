package tideware.app.tidenote.services

import android.content.Context
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import tideware.app.tidenote.R
import tideware.app.tidenote.data.model.Note
import tideware.app.tidenote.ui.adapter.FavoriteClickListener

class FavoriteService {

    public fun changeFavorite(
        context: Context,
        note: Note,
        mFavorite: ToggleButton?,
        favoriteClickListener: FavoriteClickListener? = null
    ){
       if (note.favorite)
        {

           mFavorite?.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_checked_star))



        }else{
            mFavorite?.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_unchecked_star))

        }
        /*

        mFavorite?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                note.favorite = true
                mFavorite?.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_checked_star))
                if (favoriteClickListener != null) {
                    favoriteClickListener.onFavoriteClickListener(note)
                }
            }
            else
            {
                note.favorite = false
                mFavorite?.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_unchecked_star))
                if (favoriteClickListener != null) {
                    favoriteClickListener.onFavoriteClickListener(note)
                }
            }
        }*/
    }
    object FavoriteService{
        var check = false

    }
}
