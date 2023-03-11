package screen

import NoteDatabase
import console.DefaultConsole
import model.Note
import model.NoteRepository

class NoteRepositoryNewScreen(val database: NoteDatabase,
                              val console: DefaultConsole) : Screen(database) {
    override fun showMenu(): String {
        var menu = StringBuilder()
        menu.append("============================================")
        menu.append(System.lineSeparator())
        menu.append("0. Выход")
        menu.append(System.lineSeparator())
        menu.append("1. Создание нового архива")
        menu.append(System.lineSeparator())
        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int): Screen {
        if (numberOfAction != 0) {
            var newRepositoryNoteName: String
            while (true) {
                console.print("Введите имя нового архива")
                newRepositoryNoteName = console.read()
                when  {
                    newRepositoryNoteName == null || newRepositoryNoteName == "" -> console.print("Имя не может быть пустым")
                    else -> break
                }
            }
            database.addNoteRepository(NoteRepository(0, newRepositoryNoteName, mutableListOf<Note>()))
            return NoteListScreen(newRepositoryNoteName, database)
        }
        return NoteRepositoryListScreen(database)
    }
}