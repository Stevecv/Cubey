package cubey.cubey.minecraft.commands

import cubey.cubey.minecraft.commands.utility.Command
import cubey.cubey.minecraft.commands.utility.CommandArgument
import cubey.cubey.minecraft.commands.utility.CommandData
import org.bukkit.command.CommandSender

class Cubey : Command() {
    override val data = CommandData("Cubey", "Some Descripton")
        .maxHelpLineSize(10)
        .helpCommand()
        .subcommand("Example", "", "Example Command", this::exampleCommand)
        .build()


    private fun exampleCommand(data: CommandArgument, sender: CommandSender, args: Array<String>) {
        sender.sendMessage(data.msg("Some %pprimary %ssecondary %eerror %rreset"))
    }


}