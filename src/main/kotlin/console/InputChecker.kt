package console

class InputChecker(val console: DefaultConsole) {

    fun getCommandFromUser() : String {
        var command: String
        while (true) {
            console.print("Введите команду")
            command = console.read()
            when {
                command.isEmpty() -> console.print("Команда не может быть пустой")
                !command.all { it.isDigit() } -> console.print("Команда должна быть числом")
                else -> break
            }
        }
        return  command
    }

    fun getNameFromUser(nameOfNewObject: String) : String {
        var name: String
        while (true) {
            console.print("Введите имя для нового объекта ${nameOfNewObject}")
            name = console.read()
            when {
                name == null || name == "" -> console.print("Имя не может быть пустым")
                else -> break
            }
        }
        return  name
    }

    fun getNameOfFieldFromUser(nameOfNewField: String) : String {
        var field: String
        while (true) {
            console.print("Введите $nameOfNewField новой заметки")
            field = console.read()
            when  {
                field == null || field == "" -> console.print("$nameOfNewField не может быть пустым")
                else -> break
            }
        }
        return  field
    }

}