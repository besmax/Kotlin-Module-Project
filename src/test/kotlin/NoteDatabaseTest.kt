import model.Note
import model.NoteRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NoteDatabaseTest {

    private val databaseTest = NoteDatabase()

    @Test
    fun addNoteRepositoryPopulatesNoteRepositories() {
        val noteRepo = NoteRepository(0, "Right after fired", mutableListOf<Note>())
        databaseTest.addNoteRepository(noteRepo)

        assertEquals(noteRepo.title, NoteDatabase.noteRepositories["Right after fired"]?.title ?: "no")
    }

    @Test
    fun addNotePopulatesNoteRepositories() {
        val noteRepo = NoteRepository(0, "Right after fired", mutableListOf<Note>())
        databaseTest.addNoteRepository(noteRepo)
        val note = Note(0, "Vengeance", "Kick boss's ass")
        databaseTest.addNote(note, "Right after fired")
        assertEquals("Kick boss's ass",
            NoteDatabase.noteRepositories["Right after fired"]?.notes?.get(0)?.text ?: "no")
    }

    @Test
    fun getListOfRepositoriesReturnsCorrectList() {
        databaseTest.populateDatabase()
        val expected = listOf<String>("Work", "Home")

        assertEquals(expected[0], databaseTest.getListOfRepositories()[0])
        assertEquals(expected[1], databaseTest.getListOfRepositories()[1])
    }

    @Test
    fun getListOfNotesFromRepository() {
        databaseTest.populateDatabase()
        val expected = listOf<Note> (Note(1, "Cleaning", "Cleaning working table and..."),
        Note(2, "Working", "Do job"),
        Note(3, "Lunching", "Eat lunch"))

        assertEquals(expected[0].text, databaseTest.getListOfNotesFromRepository("Work")[0].text)
        assertEquals(expected[1].text, databaseTest.getListOfNotesFromRepository("Work")[1].text)
        assertEquals(expected[2].text, databaseTest.getListOfNotesFromRepository("Work")[2].text)
    }

}