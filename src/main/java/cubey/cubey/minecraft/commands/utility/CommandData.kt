package cubey.cubey.minecraft.commands.utility

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import kotlin.reflect.KFunction3

typealias CommandAction = KFunction3<CommandArgument, CommandSender, Array<String>, Unit>


class CommandData : CommandArgument {

    constructor(name: String, description: String) : super(name, "", description, 0, ::handleCommandGroup) {
    }

    constructor(name: String, description: String, action: CommandAction) : super(name, "", description, 0, action) {
    }

    var maxHelpLineSize: Int = 20
    var prefix = "%p[%sCubey%p]%r"

    override val data = this


    fun prefix(prefix: String) : CommandData {
        this.prefix = prefix
        return this
    }


    fun maxHelpLineSize(size: Int) : CommandData {
        maxHelpLineSize = size
        return this
    }


    //region Chat colors

    var primaryColor   = ChatColor.WHITE
    var secondaryColor = ChatColor.GOLD
    var textColor      = ChatColor.GRAY
    var errorColor     = ChatColor.RED


    fun primaryColor(color: ChatColor) : CommandData {
        this.primaryColor = color
        return this
    }


    fun secondaryColor(color: ChatColor) : CommandData {
        this.secondaryColor = color
        return this
    }


    fun textColor(color: ChatColor) : CommandData {
        this.textColor = color
        return this
    }


    fun errorColor(color: ChatColor) : CommandData {
        this.errorColor = color
        return this
    }

    //endregion




    fun msg(message: String, withPrefix: Boolean = true): String {
        val msg = (if (withPrefix) "$prefix $message" else message)
            .replace("%p".toRegex(),   primaryColor.toString())
            .replace("%s".toRegex(), secondaryColor.toString())
            .replace("%r".toRegex(),      textColor.toString())
            .replace("%e".toRegex(),     errorColor.toString())

        return ChatColor.translateAlternateColorCodes('&', msg)
    }
}