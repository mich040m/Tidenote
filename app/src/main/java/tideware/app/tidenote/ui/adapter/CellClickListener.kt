package tideware.app.tidenote.ui.adapter

import tideware.app.tidenote.model.Note

interface CellClickListener {
    fun onCellClickListener(note: Note)
}