package me.kmatias.wilmacli.commands

import me.kmatias.wilmacli.Command
import me.kmatias.wilmacli.Main

class HelpCommand: Command("Help", arrayOf("h")) {
    override fun exec(args: Array<String>): Boolean {

        if (args.isEmpty()) {
            Main.commandManager.commands.forEach {
                println(it.getName())
                println()
            }

            return true
        }

        val command = Main.commandManager.getCommand(args[0], ignoreCase = true, aliases = false) ?: return false

        println(command.getHelp())

        return true
    }

    override fun needsLogin(): Boolean {
        return false
    }

    override fun getHelp(): String {
        return """
            <help|h> [command]
            This message also pops up if you typed the <command> wrong.
        """.trimIndent()
    }
}