package me.kmatias.wilmacli.commands

import kotlinx.coroutines.runBlocking
import me.kmatias.wilmacli.Command
import me.kmatias.wilmacli.Main
import org.openwilma.kotlin.classes.WilmaServer

class ServerCommand : Command("Server", arrayOf("login", "l", "s")) {
    override fun exec(args: Array<String>): Boolean {


        if (args.isNotEmpty()) {
            if (args[0].equals("connect", true)) {

                Main.wilmaServer = WilmaServer(args[1])
                if (!Main.wilmaServer.serverURL.startsWith("http")) {
                    Main.wilmaServer.serverURL = "https://${Main.wilmaServer.serverURL}"
                }

                println("Set server url to ${Main.wilmaServer.serverURL}.")

                println("Please enter your username.")
                val username = readln()

                println("Now enter your password.")
                val password = readln()

                println("Logging in...")
                runBlocking {
                    Main.wilmaClient.signInToWilma(Main.wilmaServer, username, password)
                    Main.loggedIn = true
                }
                println("Logged in.")

                return true
            } else if (args[0].equals("disconnect", true)) {

                return true
            }
        }

        return false
    }

    override fun needsLogin(): Boolean {
        return false
    }

    override fun getHelp(): String {
        return """
            server <connect> <ip>
            server <disconnect>
        """.trimMargin()
    }
}