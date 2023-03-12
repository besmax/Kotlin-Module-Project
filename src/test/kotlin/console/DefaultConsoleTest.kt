package console

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.*

class DefaultConsoleTest {
    var consoleTest = DefaultConsole()

    @Test
    fun consolePrints() {
        val out: OutputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(out))
        consoleTest.print("asd123")
        assertEquals(out.toString(), "asd123" + System.lineSeparator())
    }

    @Test
    fun consoleReadReturnsCorrectString() {
        val input: InputStream = ByteArrayInputStream("7".toByteArray())
        System.setIn(input)
        val actual: String = consoleTest.read()
        assertEquals("7", actual)
        System.setIn(System.`in`)
    }

}