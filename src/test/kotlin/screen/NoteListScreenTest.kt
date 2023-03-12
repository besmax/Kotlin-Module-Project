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
    val noteListScreenTest = NoteListScreen("titleRepository", databaseTest)

    @Test
    fun showMenuReturnsCorrectStringWhenNoNotes() {
        Mockito.`when`(databaseTest.getListOfNotesFromRepository("titleRepository")).thenReturn(
            mutableListOf<Note>())
        val actual = noteListScreenTest.showMenu()

        val expected = StringBuilder()
        expected.append("============================================")
        expected.append(System.lineSeparator())
        expected.append("0. Выход")
        expected.append(System.lineSeparator())
        expected.append("1. Создать новую заметку")
        expected.append(System.lineSeparator())
        expected.append("Заметки в архиве titleRepository:")
        expected.append(System.lineSeparator())
        expected.append("В этом архиве пока нет заметок")
        expected.append(System.lineSeparator())

        assertEquals(expected.toString().trim(), actual)
    }

    @Test
    fun showMenuReturnsCorrectStringWithNotes() {
        Mockito.`when`(databaseTest.getListOfNotesFromRepository("titleRepository")).thenReturn(
            mutableListOf<Note>(Note(0, "title", "description")))
        val actual = noteListScreenTest.showMenu()

        val expected = StringBuilder()
        expected.append("============================================")
        expected.append(System.lineSeparator())
        expected.append("0. Выход")
        expected.append(System.lineSeparator())
        expected.append("1. Создать новую заметку")
        expected.append(System.lineSeparator())
        expected.append("Заметки в архиве titleRepository:")
        expected.append(System.lineSeparator())
        expected.append("2. title")
        expected.append(System.lineSeparator())

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
}