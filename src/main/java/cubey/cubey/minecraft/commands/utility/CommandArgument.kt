package cubey.cubey.minecraft.commands.utility

import org.bukkit.command.CommandSender

open class CommandArgument(
    val name: String,
    val arguments: String,
    val description: String,
    val depth: Int,
    val action: CommandAction,
    val parent: CommandArgument? = null
) {
    val isGroup get() = commands.isNotEmpty()

    open val data: CommandData get() = parent?.data!!

    val commands: MutableList<CommandArgument> = mutableListOf()


    fun execute(sender: CommandSender, args: Array<String>) {
        action.invoke(this, sender, args)
    }


    fun subcommand(name: String, arguments: String, description: String, action: CommandAction): CommandArgument {
        commands.add(CommandArgument(name, arguments, description, depth+1, action, this))
        return this
    }


    fun subcommandGroup(name: String, description: String, action: CommandAction): CommandArgument {
        val cmd = CommandArgument(name, "", description, depth+1, action, this)
        commands.add(cmd)
        return cmd
    }


    fun subcommandGroup(name: String, description: String): CommandArgument {
        val cmd = CommandArgument(name, "", description, depth+1, ::handleCommandGroup, this)

        commands.add(cmd)
        return cmd.helpCommand()
    }


    fun helpCommand(): CommandArgument {
        return subcommand("help", "[num]", "Show help for this command", ::helpCommand)
    }


    fun end(): CommandArgument {
        return parent!!
    }


    fun build(): CommandData {
        return data
    }
}