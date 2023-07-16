package me.kmatias.wilmacli.commands

import me.kmatias.wilmacli.Command
import me.kmatias.wilmacli.Main

class TimetableCommand: Command("Timetable", arrayOf()) {
    override fun exec(args: Array<String>) {
        Main.wilmaSession
    }
}