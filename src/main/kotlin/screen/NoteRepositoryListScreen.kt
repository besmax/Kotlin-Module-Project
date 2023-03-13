package screen

import NoteDatabase
import console.DefaultConsole
import console.InputChecker

class NoteRepositoryListScreen(private val database: NoteDatabase) : Screen(database) {

    override fun showMenu(): String {
        var menu = StringBuilder()
        menu.appendLine("============================================")
        menu.appendLine("0. Выход")
        menu.appendLine("1. Создать новый архив")
        menu.appendLine("   Доступные архивы:")
        var counter = 2
        for (item in database.getListOfRepositories()) {
            menu.appendLine("$counter. $item")
            counter+=1
        }
        return menu.toString().trim()
    }

    override fun handleAction(numberOfAction: Int) : Screen {
        when {
            numberOfAction == 1 -> return NoteRepositoryNewScreen(database, InputChecker(DefaultConsole()))
            numberOfAction > (database.getListOfRepositories().size + 1) -> {
                DefaultConsole().print("Архива с таким номером нет, попробуйте ещё раз")
                return NoteRepositoryListScreen(database)
            }
            else -> return NoteListScreen(NoteDatabase.noteRepositories.values.toMutableList()[numberOfAction - 2].title, database)
        }
    }

}