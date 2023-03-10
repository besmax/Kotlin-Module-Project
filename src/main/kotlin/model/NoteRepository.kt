package model

data class NoteRepository (val noteRepositoryId: Int,
                           val title: String,
                           val notes: MutableList<Note>)