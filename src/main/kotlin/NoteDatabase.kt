import model.Note
import model.NoteRepository

class NoteDatabase {

    init {
        populateDatabase()
    }

    companion object {
        val noteRepositories: MutableMap<String, NoteRepository> = mutableMapOf()
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

    fun getListOfRepositories(): List<String> {
        return noteRepositories.keys.toList()
    }

    fun getListOfNotesFromRepository(repositoryName: String): MutableList<Note> {
        return noteRepositories[repositoryName]?.notes ?: mutableListOf()
    }

    fun getNoteById() {

    }

    //for testing
    fun populateDatabase() {

        val noteRepo1 = NoteRepository(1,
            "Work",
            mutableListOf(Note(1, "Cleaning", "Cleaning working table and..."),
                Note(2, "Working", "Do job"),
                Note(3, "Lunching", "Eat lunch")))

        val noteRepo2 = NoteRepository(2,
            "Home",
            mutableListOf(Note(4, "Cleaning", "Cleaning kitchen and wash dishes"),
                Note(5, "Cooking", "Cook pasta"),
                Note(6, "Lunching", "Eat lunch")))

        noteRepositories.put(noteRepo1.title, noteRepo1)
        noteRepositories.put(noteRepo2.title, noteRepo2)
    }
}