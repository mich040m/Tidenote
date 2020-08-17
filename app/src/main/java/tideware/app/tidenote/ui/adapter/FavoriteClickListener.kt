package tideware.app.tidenote.ui.adapter

import android.content.Context
import android.widget.ToggleButton
import tideware.app.tidenote.data.model.Note

interface FavoriteClickListener {
    fun onFavoriteClickListener(
        note: Note
    )
}