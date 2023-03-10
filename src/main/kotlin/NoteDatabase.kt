import model.Note
import model.NoteRepository

class NoteDatabase {

    companion object {
        private val noteRepositories: MutableMap<String, NoteRepository> get() = mutableMapOf()
        private var NOTE_REPOSITORY_ID = 0
        private var NOTE_ID = 0
    }

    fun addNoteRepository(noteRepository: NoteRepository) {
        val newNoteRepository = NoteRepository(NOTE_REPOSITORY_ID, noteRepository.title, noteRepository.notes)
        NOTE_REPOSITORY_ID+=1
        noteRepositories.put(newNoteRepository.title, newNoteRepository)
    }

    fun addNote(note: Note, noteRepositoryTitle: String) {
        noteRepositories[noteRepositoryTitle]?.notes?.add(Note(NOTE_ID, note.title, note.text))
        NOTE_ID+=1
    }
}