package me.kmatias.wilmacli.commands

import me.kmatias.wilmacli.Command
import me.kmatias.wilmacli.Main

class QuitCommand: Command("Quit", arrayOf("q")) {
    override fun exec(args: Array<String>): Boolean {
        println("Quitting")
        Main.quitting = true
        return true
    }

    override fun needsLogin(): Boolean {
        return false
    }

    override fun getHelp(): String {
        return "quits the program"
    }
}