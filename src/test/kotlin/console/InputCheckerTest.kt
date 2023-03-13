package console

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class InputCheckerTest {

    val consoleTest = mock(DefaultConsole()::class.java)
    val inputCheckerTest = InputChecker(consoleTest)

    @Test
    fun getCommandFromUserPrintsRequest() {
        Mockito.`when`(consoleTest.read()).thenReturn("1")
        inputCheckerTest.getCommandFromUser()
        Mockito.verify(consoleTest).print("Введите команду")
    }

    @Test
    fun getCommandFromUserPrintsWhenCommandIsEmptyString() {
        Mockito.`when`(consoleTest.read()).thenReturn("").thenReturn("1")
        inputCheckerTest.getCommandFromUser()
        Mockito.verify(consoleTest).print("Команда не может быть пустой")
    }

    @Test
    fun getNameFromUserPrintsRequest() {
        Mockito.`when`(consoleTest.read()).thenReturn("1")
        inputCheckerTest.getNameFromUser("name")
        Mockito.verify(consoleTest).print("Введите имя для нового объекта name")
    }

    @Test
    fun getNameFromUserPrintsWhenNameIsEmptyString() {
        Mockito.`when`(consoleTest.read()).thenReturn("").thenReturn("0")
        inputCheckerTest.getNameFromUser("name")
        Mockito.verify(consoleTest).print("Имя не может быть пустым")
    }

    @Test
    fun getNameFromUserPrintsWhenNameIsNull() {
        Mockito.`when`(consoleTest.read()).thenReturn(null).thenReturn("0")
        inputCheckerTest.getNameFromUser("name")
        Mockito.verify(consoleTest).print("Имя не может быть пустым")
    }

    @Test
    fun getNameOfFieldFromUserPrintsRequest() {
        Mockito.`when`(consoleTest.read()).thenReturn("0")
        inputCheckerTest.getNameOfFieldFromUser("name")
        Mockito.verify(consoleTest).print("Введите name новой заметки")
    }

    @Test
    fun getNameOfFieldFromUserPrintsWhenNameIsEmpty() {
        Mockito.`when`(consoleTest.read()).thenReturn(null).thenReturn("0")
        inputCheckerTest.getNameOfFieldFromUser("name")
        Mockito.verify(consoleTest).print("name не может быть пустым")
    }

    @Test
    fun getNameOfFieldFromUserPrintsWhenNameIsNull() {
        Mockito.`when`(consoleTest.read()).thenReturn("").thenReturn("0")
        inputCheckerTest.getNameOfFieldFromUser("name")
        Mockito.verify(consoleTest).print("name не может быть пустым")
    }
}
