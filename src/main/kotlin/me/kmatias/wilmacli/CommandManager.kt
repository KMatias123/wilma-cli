package me.kmatias.wilmacli

import me.kmatias.wilmacli.commands.HelpCommand
import me.kmatias.wilmacli.commands.QuitCommand
import me.kmatias.wilmacli.commands.ServerCommand

class CommandManager {

    val commands = HashSet<Command>()

    fun init() {
        commands.add(HelpCommand())
        commands.add(QuitCommand())
        commands.add(ServerCommand())
    }

    fun runCommand(cmdInput: String, args: Array<String>): Boolean {
        commands.forEach{ command ->
            if (command.getName().equals(cmdInput, true) || command.getAliases().any { it.equals(cmdInput, true) }) {
                command.exec(args)
                return true
            }
        }
        return false
    }
}