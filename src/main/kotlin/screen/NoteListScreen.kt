package screen

import NoteDatabase
import console.DefaultConsole
import console.InputChecker

class NoteListScreen (val noteRepositoryName: String,
                      val database: NoteDatabase) : Screen(database) {

    var listOfItems =   database.getListOfNotesFromRepository(noteRepositoryName)
    override fun showMenu(): String {
        listOfItems =   database.getListOfNotesFromRepository(noteRepositoryName)
        val menu = StringBuilder()
        menu.append("============================================")
        menu.append(System.lineSeparator())
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
        listOfItems =   database.getListOfNotesFromRepository(noteRepositoryName)
        when {
            numberOfAction == 0 -> return NoteRepositoryListScreen(database)
            numberOfAction == 1 -> return NoteNewScreen(database, noteRepositoryName, InputChecker(DefaultConsole()))
            numberOfAction > (listOfItems.size + 1) -> {
                DefaultConsole().print("Заметки с таким номером нет, попробуйте ещё раз")
                return NoteListScreen(noteRepositoryName, database)
            }
                    else -> return NoteScreen(database, listOfItems.get(numberOfAction - 2), noteRepositoryName)
        }
    }
}