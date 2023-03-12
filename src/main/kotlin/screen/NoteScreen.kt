package screen

import NoteDatabase
import model.Note

class NoteScreen(val database: NoteDatabase,
                 val note: Note,
                 val noteRepositoryName: String) : Screen(database) {
    override fun showMenu(): String {
        val menu = StringBuilder()
        menu.append("============================================")
        menu.append(System.lineSeparator())
        menu.append("0. Выход")
        menu.append(System.lineSeparator())
        menu.append("Название архива заметки: $noteRepositoryName")
        menu.append(System.lineSeparator())
        menu.append("Название заметки: ${note.title}")
        menu.append(System.lineSeparator())
        menu.append("Текст заметки: ${note.text}")
        menu.append(System.lineSeparator())
        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int): Screen {
        return NoteListScreen(noteRepositoryName, database)
    }
}