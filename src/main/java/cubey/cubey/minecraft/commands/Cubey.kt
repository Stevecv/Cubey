package cubey.cubey.minecraft.commands

import cubey.cubey.minecraft.Cubey
import cubey.cubey.minecraft.commands.utility.Command
import cubey.cubey.minecraft.commands.utility.CommandArgument
import cubey.cubey.minecraft.commands.utility.CommandData
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import java.io.File

class Cubey : Command() {
    override val data = CommandData("Cubey", "Some Descripton")
        .maxHelpLineSize(10)
        .helpCommand()
        .subcommand("New", "Project name", "Create a new project", this::newProject)
        .subcommand("Compile", "Project name", "Compile your code", this::compile)
        .build()


    private fun compile(data: CommandArgument, sender: CommandSender, args: Array<String>) {
        sender.sendMessage(data.msg("Some %pprimary %ssecondary %eerror %rreset"))
    }
    private fun newProject(data: CommandArgument, sender: CommandSender, args: Array<String>) {
        val plugin = Cubey.getInstance()
        if (args.size > 0) {
            val name = args[0]

            val project = File(plugin.dataFolder.toString() + File.separator + name + File.separator + "{1}".format(name))
            if (project.exists()) {
                data.msg("%eThere is already a project named {1}!".format(name))
                return
            }
            if (!project.exists()) try {
                project.mkdir()
                project.createNewFile()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            File(project.path).writeText("Name: {1}\n" +
                    "Author: {2}\n" +
                    "Description: A plugin crafted by {2} using Crafting Table\n" +
                    "Version: 1.0.0-ALPHA".format(name, sender.name))
        } else {
            data.msg("%eYou must enter a name for your project!")
        }
    }
}