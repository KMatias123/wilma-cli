package me.kmatias.wilmacli.commands

import me.kmatias.wilmacli.Command
import me.kmatias.wilmacli.Main

class QuitCommand: Command("Quit", arrayOf("q")) {
    override fun exec(args: Array<String>) {
        println("Quitting")
        Main.quitting = true
    }
}