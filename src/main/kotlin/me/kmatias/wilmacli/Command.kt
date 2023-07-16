package me.kmatias.wilmacli

abstract class Command(name: String, aliases: Array<String>) {
    private val name: String
    private val aliases: Array<String>

    init {
        this.name = name
        this.aliases = aliases
    }

    abstract fun exec(args: Array<String>)

    fun getName(): String {
        return name
    }

    fun getAliases(): Array<String> {
        return aliases
    }
}