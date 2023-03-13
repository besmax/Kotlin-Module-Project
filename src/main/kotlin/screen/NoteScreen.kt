package screen

import NoteDatabase
import model.Note

class NoteScreen(
    private val database: NoteDatabase,
    private val note: Note,
    private val noteRepositoryName: String) : Screen(database) {
    override fun showMenu(): String {
        val menu = StringBuilder()
        menu.appendLine("============================================")
        menu.appendLine("0. Выход")
        menu.appendLine("Название архива заметки: $noteRepositoryName")
        menu.appendLine("Название заметки: ${note.title}")
        menu.appendLine("Текст заметки: ${note.text}")
        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int): Screen {
        return NoteListScreen(noteRepositoryName, database)
    }
}