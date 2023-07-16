package me.kmatias.wilmacli.commands

import me.kmatias.wilmacli.Command

class HelpCommand: Command("Help", arrayOf("h")) {
    override fun exec(args: Array<String>) {
        println("""
            
        """.trimIndent())
    }
}