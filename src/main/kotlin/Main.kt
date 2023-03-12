import console.DefaultConsole
import console.InputChecker
import screen.NoteRepositoryListScreen
import screen.Screen

fun main() {

    val console = DefaultConsole()
    val database = NoteDatabase()
    val inputChecker = InputChecker(console)
    database.populateDatabase()

    var currentScreen: Screen = NoteRepositoryListScreen(database)

    while (true) {
        console.print(currentScreen.showMenu())
        var command = inputChecker.getCommandFromUser()

        if (command.toInt() == 0 && currentScreen is NoteRepositoryListScreen) return
        else currentScreen = currentScreen.handleAction(command.toInt())
    }
}
