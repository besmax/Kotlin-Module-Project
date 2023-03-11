import console.DefaultConsole
import screen.NoteRepositoryListScreen
import screen.Screen

fun main() {

    val console = DefaultConsole()
    val database = NoteDatabase()
    val noteRepositoryListScreen = NoteRepositoryListScreen(database)
    database.populateDatabase()

    var currentScreen: Screen = noteRepositoryListScreen
    var previousScreen: Screen = noteRepositoryListScreen

    while (true) {
        console.print(currentScreen.showMenu())
        var command: String
        while (true) {
            console.print("Введите команду")
            command = console.read()
            when  {
                command == null -> console.print("Команда не может быть null")
                command == "" -> console.print("Команда не может быть пустой")
                !command.all { it.isDigit() } -> console.print("Команда должна быть числом")
                else -> break
            }
        }

        if (command.toInt() == 0 && currentScreen is NoteRepositoryListScreen) return
        else {
            previousScreen = currentScreen
            currentScreen = currentScreen.handleAction(command.toInt())
        }

    }

}
