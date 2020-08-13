package tideware.app.tidenote.ui.adapter

import tideware.app.tidenote.data.model.Note

interface CellClickListener {
    fun onCellClickListener(note: Note)
}