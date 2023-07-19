package me.kmatias.wilmacli

import me.kmatias.wilmacli.commands.HelpCommand
import me.kmatias.wilmacli.commands.QuitCommand
import me.kmatias.wilmacli.commands.ServerCommand
import me.kmatias.wilmacli.commands.TimetableCommand

class CommandManager {

    val commands = HashSet<Command>()

    fun init() {
        commands.add(HelpCommand())
        commands.add(QuitCommand())
        commands.add(ServerCommand())
        commands.add(TimetableCommand())
    }

    /**
     * @return print help tip
     */
    fun runCommand(cmdInput: String, args: Array<String>): Boolean {

        val command = getCommand(cmdInput, ignoreCase = true, aliases = true) ?: return true

        if (!Main.loggedIn && command.needsLogin()) {
            println("You need to connect to the wilma server first (use the server command)")
            return false
        }

        if (!command.exec(args)) {
            println(command.getHelp())
        }
        return false
    }

    fun getCommand(name: String, ignoreCase: Boolean, aliases: Boolean): Command? {
        commands.forEach { command ->
            if (command.getName().equals(name, ignoreCase) || command.getAliases().any {
                aliases && it.equals(name, ignoreCase)
            }
                ) {
                return command
            }
        }
        return null
    }
}