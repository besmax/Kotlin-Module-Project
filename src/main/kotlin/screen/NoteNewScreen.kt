package screen

import NoteDatabase
import console.InputChecker
import model.Note

class NoteNewScreen(
    private val database: NoteDatabase,
    private val noteRepositoryName: String,
    private val inputChecker: InputChecker) : Screen(database) {
    override fun showMenu(): String {
        var menu = StringBuilder()
        menu.appendLine("============================================")
        menu.appendLine("0. Выход")
        menu.appendLine("1. Создать новую заметку в архиве $noteRepositoryName")

        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int): Screen {
        if (numberOfAction != 0) {
            var newNoteName = inputChecker.getNameFromUser("заметка")
            var newNoteText = inputChecker.getNameOfFieldFromUser("текст")
            database.addNote(Note(0, newNoteName, newNoteText), noteRepositoryName)
        }
        return NoteListScreen(noteRepositoryName, database)
    }
}