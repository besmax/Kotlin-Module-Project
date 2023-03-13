package screen

import NoteDatabase
import console.DefaultConsole
import console.InputChecker

class NoteListScreen (
    private val noteRepositoryName: String,
    private val database: NoteDatabase) : Screen(database) {

    override fun showMenu(): String {
        val listOfItems =   database.getListOfNotesFromRepository(noteRepositoryName)
        val menu = StringBuilder()
        menu.appendLine("============================================")
        menu.appendLine("0. Выход")
        menu.appendLine("1. Создать новую заметку")
        menu.appendLine("Заметки в архиве $noteRepositoryName:")

        if  (listOfItems.isEmpty()) {
            menu.appendLine("В этом архиве пока нет заметок")
        }
        var counter = 2
        for (item in listOfItems) {
            menu.appendLine("$counter. ${item.title}")
            counter+=1
        }
        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int): Screen {
        val listOfItems =   database.getListOfNotesFromRepository(noteRepositoryName)
        return when {
            numberOfAction == 0 -> NoteRepositoryListScreen(database)
            numberOfAction == 1 -> NoteNewScreen(database, noteRepositoryName, InputChecker(DefaultConsole()))
            numberOfAction > (listOfItems.size + 1) -> {
                DefaultConsole().print("Заметки с таким номером нет, попробуйте ещё раз")
                NoteListScreen(noteRepositoryName, database)
            }

            else -> NoteScreen(database, listOfItems.get(numberOfAction - 2), noteRepositoryName)
        }
    }
}