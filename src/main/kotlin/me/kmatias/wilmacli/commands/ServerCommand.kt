package me.kmatias.wilmacli.commands

import kotlinx.coroutines.runBlocking
import me.kmatias.wilmacli.Command
import me.kmatias.wilmacli.Main
import org.openwilma.kotlin.OpenWilma
import org.openwilma.kotlin.classes.WilmaServer

class ServerCommand: Command("Server", arrayOf("login", "l", "s")) {
    override fun exec(args: Array<String>) {

        args.forEach {

            println(it)

        }

        var ranCommand = false

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
                    Main.wilmaSession = OpenWilma.signInToWilma(Main.wilmaServer, username, password)
                }
                println("Logged in.")

                ranCommand = true
            } else if (args[0].equals("disconnect", true)) {

                ranCommand = true
            }
        }

        if (!ranCommand) {
            println("""
                server <connect> [ip]
                server <disconnect>
            """.trimMargin())
        }
    }
}