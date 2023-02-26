package cubey.cubey.minecraft.commands.utility

import cubey.cubey.minecraft.commands.utility.builders.append
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.command.CommandSender
import java.lang.Integer.max
import java.lang.Integer.min

val chars = arrayOf(
    '┣',
    '┃',
    '┗'
)

fun helpCommand(arg: CommandArgument, sender: CommandSender, args: Array<String>) {
    val cmd = arg.parent!!
    val data = arg.data

    val name   = cmd.data.name
    val subCmd = args.copyOf(cmd.depth)

    val index = max((if (args.size > cmd.depth+1) args[cmd.depth+1] else "1").toInt(), 1)

    val maxLength  = arg.data.maxHelpLineSize-3
    val skip       = (index-1) * maxLength
    val loopAmount = skip + maxLength
    val totalCmds  = cmd.commands.size
    val pageCount  = (totalCmds / maxLength) + 1

    //  Checking if the health page is empty

    if (skip > cmd.commands.size) return sender.sendMessage(data.msg("Help page %p$index %rdoes not exist."))

    //  Build the header of the message

    val subCmdArg = if (subCmd.isEmpty()) "" else " ${subCmd.joinToString(" ")}"

    val comp = Component.text()
        .append(Component.text(data.msg("&m---------------------------------------------\n")))
        .append(data.msg("/%p"))
        .append(Component.text(name + subCmdArg))
//            .setHoverText(data.msg("%rRun command %p/$name help", false))
//            .setCommand("/$name help"))
        .append("  ")

    //  Build the fancy pants arrow thingy
    if (pageCount > 1) {
        val arrowComp1 = Component.text("«")
        val arrowComp2 = Component.text("»")

        comp.append(arrowComp1)
            .append(data.msg(" %r$index/$pageCount ", false))
            .append(arrowComp2)
    }

    comp.append("\n")

    //  Build the body of the message

    val commandsThisPage = min(loopAmount, totalCmds)-1

    for (i in skip until loopAmount) {
        if(i >= cmd.commands.size)
            comp.append(data.msg("\n"))

        else {
            val char = chars[if (i == commandsThisPage) 2 else 0]
            val subName = cmd.commands[i].name
            val args = cmd.commands[i].arguments

            if (args == "") comp.append(Component.text(data.msg("  %r$char %p$subName %r- ${cmd.commands[i].description}\n")))
//                    .setHoverText(data.msg("%rRun command /%p$name$subCmdArg $subName", false))
//                    .setCommand("/$name$subCmdArg $subName"))

            //  Return default
            else comp.append(Component.text(data.msg("  %r$char %p$subName %s$args %r- ${cmd.commands[i].description}\n")))
//                    .setHoverText(data.msg("%rFill /%p$name$subCmdArg $subName %s${args}", false))
//                    .setSuggestion("/$name$subCmdArg $subName"))
        }
    }

    //  Build the footer of the message

    comp.append(data.msg("&m---------------------------------------------"))

    //  Send the message

    sender.sendMessage(comp);
}


fun handleCommandGroup(arg: CommandArgument, sender: CommandSender, args: Array<String>) {
    val prefix = arg.data.prefix
    val data   = arg.data
    val name   = arg.data.name
    val subCmd = args.getOrNull(arg.depth)

    //  Check if we have subcommands

    if (arg.commands.size == 0) return sender.sendMessage(data.msg("This command has no subcommands."))

    //  Checking if a subcommand has been sent

    if (subCmd == null) return arg.commands.first().execute(sender, args)

    //  Find the subcommand

    val cmd = arg.commands.find { it.name.equals(subCmd, true) }
    if(cmd != null) return cmd.execute(sender, args)

    //  Remove the subcommand from the arguments

    val subCmds = args.copyOf(arg.depth)
    val b = if (subCmds.isEmpty()) "help" else "${subCmds.joinToString(" ")} help"

    //  Sending the help message

    sender.sendMessage(Component.text(data.msg("Subcommand %p$subCmd %rdoes not exist!\n$prefix Try /%p"))
//        .append(Component.text("$name $b")
//            .setHoverText(data.msg("%rRun command /%p$name $b", false))
//            .setCommand("/$name $b")
    )
}