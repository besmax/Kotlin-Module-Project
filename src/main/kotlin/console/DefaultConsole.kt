package console

import java.util.*

class DefaultConsole : Console {
    override fun print(text: String) {
        println(text)
    }

    override fun read(): String {
        return Scanner(System.`in`).nextLine()
    }
}