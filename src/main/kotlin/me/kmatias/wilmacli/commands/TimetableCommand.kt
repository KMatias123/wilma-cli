package me.kmatias.wilmacli.commands

import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import me.kmatias.wilmacli.Command
import me.kmatias.wilmacli.Main
import org.openwilma.kotlin.enums.LessonNoteRange
import org.openwilma.kotlin.utils.LocalDateGSONAdapter
import org.openwilma.kotlin.utils.LocalDateTimeGSONAdapter
import org.openwilma.kotlin.utils.LocalTimeGSONAdapter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class TimetableCommand : Command("Timetable", arrayOf()) {

    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .registerTypeAdapter(LocalDate::class.java, LocalDateGSONAdapter())
        .registerTypeAdapter(LocalTime::class.java, LocalTimeGSONAdapter())
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeGSONAdapter())
        .create()

    override fun exec(args: Array<String>): Boolean {

        if (args.isEmpty()) {
            return false
        }

        if (args[0].equals("default", true)) {
            runBlocking {
                val notes = Main.wilmaClient.lessonNotes(LessonNoteRange.DEFAULT)
                println(gson.toJson(notes))
            }

            return true
        } else if (args[0].equals("custom", true)) {

            if (args.size < 3) { // not enough args
                return false
            }

            val format = SimpleDateFormat("dd/MM/yyyy")

            val startDate = format.parse(args[1]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            val endDate = format.parse(args[2]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

            runBlocking {
                val notes = Main.wilmaClient.lessonNotes(LessonNoteRange.CUSTOM, start = startDate, end = endDate)
                for (note in notes) {
                    println("${note.courseName} ${note.authorName}")
                }
            }
            return true
        } else if (args[0].equals("year", true)) {

            return true
        }
        return false
    }

    override fun getHelp(): String {
        return """
            Returns the timetable
            
            <timetable> [default|year]
            <timetable> [custom] <dd/MM/yyyy> <dd/MM/yyyy>
        """.trimIndent()
    }
}