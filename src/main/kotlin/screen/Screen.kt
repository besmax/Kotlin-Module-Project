package screen

import NoteDatabase
import console.Console
import console.DefaultConsole

abstract class Screen (private val database: NoteDatabase) {

    abstract fun showMenu() : String

    abstract fun handleAction(numberOfAction: Int) : Screen
}