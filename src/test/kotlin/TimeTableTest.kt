
import me.kmatias.wilmacli.Main
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertContains

class TimeTableTest {

    @Test
    fun timeTable() {
        val input = """
                server connect espoondemo.inschool.fi
                oppilas
                oppilas
                timetable custom 01/01/2023 01/06/2023
            """.trimIndent().encodeToByteArray()
        val inStream = ByteArrayInputStream(input)

        System.setIn(inStream)

        val stream = ByteArrayOutputStream()
        val print = PrintStream(stream)
        System.setOut(print)


        Main.main(arrayOf())
        print.flush()

        assertContains(stream.toString(), """
            null Aija Aituri
            null Bertta Berlin
            null Carolyn Cannes
            null Aune Asiakirja
        """.trimIndent())


        print.close()
        inStream.close()
    }
}