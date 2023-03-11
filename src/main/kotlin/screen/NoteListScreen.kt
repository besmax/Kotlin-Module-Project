package screen

import NoteDatabase
import NoteDatabase.Companion.noteRepositories
import console.DefaultConsole

class NoteListScreen (val noteRepositoryName: String, val database: NoteDatabase) : Screen(database) {

    val listOfItems =   database.getListOfNotesFromRepository(noteRepositoryName)
    override fun showMenu(): String {
        val menu = StringBuilder()
        menu.append("0. Выход")
        menu.append(System.lineSeparator())
        menu.append("1. Создать новую заметку")
        menu.append(System.lineSeparator())
        menu.append("Заметки в архиве $noteRepositoryName:")
        menu.append(System.lineSeparator())

        if  (listOfItems.isEmpty()) {
            menu.append("В этом архиве пока нет заметок")
            menu.append(System.lineSeparator())
        }

        if (listOfItems != null) {
            var counter = 2
            for (item in listOfItems) {
                menu.append("$counter. ${item.title}")
                menu.append(System.lineSeparator())
                counter+=1
            }
        }
        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int): Screen {
        when (numberOfAction) {
            0 -> return NoteRepositoryListScreen(database)
            1 -> return NoteNewScreen(database, DefaultConsole(), noteRepositoryName)
            else -> return NoteScreen(database, listOfItems.get(numberOfAction - 2), noteRepositoryName)
        }
    }
}