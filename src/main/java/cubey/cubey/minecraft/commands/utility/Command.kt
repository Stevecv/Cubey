package cubey.cubey.minecraft.commands.utility

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

abstract class Command : CommandExecutor {
    abstract val data: CommandData


    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): Boolean {
        try {
            data.execute(sender, args)
        }
        catch (e: CommandError) {
            sender.sendMessage(data.msg(e.message ?: "An error occurred."))
        }
        catch (e: Exception) {
            sender.sendMessage(data.msg("An error occurred while executing this command."))
            e.printStackTrace()
            return true
        }

        return true
    }
}