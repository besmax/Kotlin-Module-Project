package screen

import NoteDatabase
import console.InputChecker
import model.Note
import model.NoteRepository

class NoteRepositoryNewScreen(
    private val database: NoteDatabase,
    private val inputChecker: InputChecker) : Screen(database) {
    override fun showMenu(): String {
        var menu = StringBuilder()
        menu.appendLine("============================================")
        menu.appendLine("0. Выход")
        menu.appendLine("1. Создание нового архива")
        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int): Screen {
        if (numberOfAction != 0) {
            var newRepositoryNoteName = inputChecker.getNameFromUser("архив")

            database.addNoteRepository(NoteRepository(0, newRepositoryNoteName, mutableListOf<Note>()))
            return NoteListScreen(newRepositoryNoteName, database)
        }
        return NoteRepositoryListScreen(database)
    }
}