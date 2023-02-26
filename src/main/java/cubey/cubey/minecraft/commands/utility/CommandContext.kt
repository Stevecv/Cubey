package cubey.cubey.minecraft.commands.utility

import org.bukkit.command.CommandSender

class CommandContext(
    val sender:  CommandSender,
    val command: String,
    val label:   String?,
    val args:    Array<String>
) {
}