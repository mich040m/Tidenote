package tideware.app.tidenote.services

import android.content.Context
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import tideware.app.tidenote.R
import tideware.app.tidenote.data.model.Note

class FavoriteService {
    public fun changeFavorite(context: Context, note: Note, mFavorite: ToggleButton?){
        if (note.favorite)
        {
            mFavorite?.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_checked_star))
            mFavorite?.isChecked = true
        }else{
            mFavorite?.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_unchecked_star))
            mFavorite?.isChecked = false
        }

        mFavorite?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                note.favorite = true
                mFavorite?.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_checked_star))
            }
            else
            {
                note.favorite = false
                mFavorite?.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_unchecked_star))
            }
        }
    }
}