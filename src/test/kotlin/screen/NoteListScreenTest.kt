package screen

import NoteDatabase
import console.DefaultConsole
import console.InputChecker
import model.Note
import model.NoteRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito


class NoteListScreenTest {

    val databaseTest = mock(NoteDatabase::class.java)
    val noteRepositoryTest = NoteRepository(0,
        "архивЪ",
        mutableListOf(Note(0, "title", "description"))
    )
    private val noteListScreenTest = NoteListScreen("titleRepository", databaseTest)

    @Test
    fun showMenuReturnsCorrectStringWhenNoNotes() {
        Mockito.`when`(databaseTest.getListOfNotesFromRepository("titleRepository")).thenReturn(
            mutableListOf<Note>())
        val actual = noteListScreenTest.showMenu()

        val expected = StringBuilder()
        expected.appendLine("============================================")
        expected.appendLine("0. Выход")
        expected.appendLine("1. Создать новую заметку")
        expected.appendLine("Заметки в архиве titleRepository:")
        expected.appendLine("В этом архиве пока нет заметок")

        assertEquals(expected.toString().trim(), actual)
    }

    @Test
    fun showMenuReturnsCorrectStringWithNotes() {
        Mockito.`when`(databaseTest.getListOfNotesFromRepository("titleRepository")).thenReturn(
            mutableListOf<Note>(Note(0, "title", "description")))
        val actual = noteListScreenTest.showMenu()

        val expected = StringBuilder()
        expected.appendLine("============================================")
        expected.appendLine("0. Выход")
        expected.appendLine("1. Создать новую заметку")
        expected.appendLine("Заметки в архиве titleRepository:")
        expected.appendLine("2. title")

        assertEquals(expected.toString().trim(), actual)
    }

    @Test
    fun handleActionOn0() {
        val actual = noteListScreenTest.handleAction(0)
        val expected = NoteRepositoryListScreen(databaseTest)

        assertEquals(actual.javaClass, expected.javaClass)
    }

    @Test
    fun handleActionOn1() {
        val actual = noteListScreenTest.handleAction(1)
        val expected = NoteNewScreen(databaseTest, "xxx", InputChecker(DefaultConsole()))

        assertEquals(actual.javaClass, expected.javaClass)
    }

    @Test
    fun handleActionOnNumberOfExistingNote() {
        Mockito.`when`(databaseTest.getListOfNotesFromRepository("titleRepository")).thenReturn(
            mutableListOf<Note>(Note(0, "title", "description")))
        val actual = noteListScreenTest.handleAction(2)
        val expected = NoteScreen(databaseTest, Note(0, "title", "description"), "titleRepository")

        assertEquals(actual.javaClass, expected.javaClass)
        assertEquals(actual.showMenu(), expected.showMenu())
    }

    @Test
    fun handleActionOnNumberOfNonExistingNote() {
        Mockito.`when`(databaseTest.getListOfNotesFromRepository("titleRepository")).thenReturn(
            mutableListOf<Note>(Note(0, "title", "description")))
        val actual = noteListScreenTest.handleAction(3)
        val expected = NoteListScreen("titleRepository", databaseTest)

        assertEquals(actual.javaClass, expected.javaClass)
        assertEquals(actual.showMenu(), expected.showMenu())
    }
}