package screen

import NoteDatabase
import console.DefaultConsole
import console.InputChecker
import model.Note

class NoteNewScreen(val database: NoteDatabase,
                    val console: DefaultConsole,
                    val noteRepositoryName: String,
                    val inputChecker: InputChecker) : Screen(database) {
    override fun showMenu(): String {
        var menu = StringBuilder()
        menu.append("============================================")
        menu.append(System.lineSeparator())
        menu.append("0. Выход")
        menu.append(System.lineSeparator())
        menu.append("1. Создать новую заметку в архиве $noteRepositoryName")
        menu.append(System.lineSeparator())

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