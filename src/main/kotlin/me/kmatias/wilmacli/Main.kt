package me.kmatias.wilmacli

import org.openwilma.kotlin.OpenWilma
import org.openwilma.kotlin.classes.WilmaServer

class Main {

    companion object {
        lateinit var wilmaServer: WilmaServer
        val wilmaClient = OpenWilma()
        var loggedIn = false

        var commandManager = CommandManager()

        var quitting = false

        @JvmStatic
        fun main(args: Array<String>) {
            commandManager.init()

            try {
                while (!quitting) {
                    print("> ")
                    val input = readln()

                    val split = input.split(" ")

                    if (split[0].isNotEmpty() && commandManager.runCommand(split[0], split.drop(1).toTypedArray())) {
                        println("Invalid command. Try \"help\" for help.")
                    }
                }
            } catch (e: RuntimeException) {
                e.printStackTrace()
                println(
                    """
            
            
                    EOF was reached? Please create an issue
                """.trimIndent()
                )
            }
        }
    }
}
