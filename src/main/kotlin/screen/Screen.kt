package screen

import NoteDatabase

abstract class Screen (private val database: NoteDatabase) {

    abstract fun showMenu() : String

    abstract fun handleAction(numberOfAction: Int) : Screen
}