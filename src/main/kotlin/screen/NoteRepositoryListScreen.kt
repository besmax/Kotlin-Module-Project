package screen

import NoteDatabase
import console.DefaultConsole
import console.InputChecker


class NoteRepositoryListScreen(val database: NoteDatabase) : Screen(database) {

    override fun showMenu(): String {
        var menu = StringBuilder()
        menu.append("============================================")
        menu.append(System.lineSeparator())
        menu.append("0. Выход")
        menu.append(System.lineSeparator())
        menu.append("1. Создать новый архив")
        menu.append(System.lineSeparator())
        menu.append("   Доступные архивы:")
        menu.append(System.lineSeparator())
        var counter = 2
        for (item in database.getListOfRepositories()) {
            menu.append("$counter. $item")
            menu.append(System.lineSeparator())
            counter+=1
        }
        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int) : Screen {
        when (numberOfAction) {
            1 -> return NoteRepositoryNewScreen(database, InputChecker(DefaultConsole()))
            else -> return NoteListScreen(NoteDatabase.noteRepositories.values.toMutableList()[numberOfAction - 2].title, database)
        }
    }

}