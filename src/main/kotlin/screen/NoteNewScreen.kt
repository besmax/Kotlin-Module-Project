package screen

import NoteDatabase
import console.DefaultConsole
import model.Note
import model.NoteRepository

class NoteNewScreen(val database: NoteDatabase,
                    val console: DefaultConsole,
                    val noteRepositoryName: String) : Screen(database) {
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
            var newNoteName: String
            while (true) {
                console.print("Введите имя новой заметки")
                newNoteName = console.read()
                when  {
                    newNoteName == null || newNoteName == "" -> console.print("Имя не может быть пустым")
                    else -> break
                }
            }
            var newNoteText: String
            while (true) {
                console.print("Введите текст новой заметки")
                newNoteText = console.read()
                when  {
                    newNoteText == null || newNoteText == "" -> console.print("Текст не может быть пустым")
                    else -> break
                }
            }
            database.addNote(Note(0, newNoteName, newNoteText), noteRepositoryName)
        }
        return NoteListScreen(noteRepositoryName, database)
    }
}