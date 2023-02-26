package cubey.cubey.minecraft.commands.utility

import com.jeroenvdg.rounds.command.util.builders.*
import com.jeroenvdg.rounds.utils.tryParseInt
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
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

    val index = max((if (args.size > cmd.depth+1) args[cmd.depth+1] else "1").tryParseInt() ?: 1, 1)

    val maxLength  = arg.data.maxHelpLineSize-3
    val skip       = (index-1) * maxLength
    val loopAmount = skip + maxLength
    val totalCmds  = cmd.commands.size
    val pageCount  = (totalCmds / maxLength) + 1

    //  Checking if the health page is empty

    if (skip > cmd.commands.size) return sender.sendMessage(data.msg("Help page %p$index %rdoes not exist."))

    //  Build the header of the message

    val subCmdArg = if (subCmd.isEmpty()) "" else " ${subCmd.joinToString(" ")}"

    val comp = TextComponent()
        .append(data.msg("&m---------------------------------------------\n"))
        .append(data.msg("/%p"))
        .append(TextComponent(name + subCmdArg)
            .setHoverText(data.msg("%rRun command %p/$name help", false))
            .setCommand("/$name help"))
        .append("  ")

    //  Build the fancy pants arrow thingy
    if (pageCount > 1) {
        val arrowComp1 = TextComponent("«")
        arrowComp1.color      = if (index == 1) data.textColor else data.secondaryColor
        arrowComp1.clickEvent = if (index == 1) null else ClickEvent(ClickEvent.Action.RUN_COMMAND, "/$name${subCmdArg} help ${index-1}")
        arrowComp1.hoverEvent = if (index == 1) null else HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent("${data.secondaryColor}Go to previous page.")))

        val arrowComp2 = TextComponent("»")
        arrowComp2.color      = if (index >= pageCount) data.textColor else data.secondaryColor
        arrowComp2.clickEvent = if (index >= pageCount) null else ClickEvent(ClickEvent.Action.RUN_COMMAND, "/$name${subCmdArg} help ${index+1}")
        arrowComp2.hoverEvent = if (index >= pageCount) null else HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent("${data.secondaryColor}Go to next page.")))

        comp.append(arrowComp1)
            .append(data.msg(" %r$index/$pageCount ", false))
            .append(arrowComp2)
    }

    comp.addExtra("\n")

    //  Build the body of the message

    val commandsThisPage = min(loopAmount, totalCmds)-1

    for (i in skip until loopAmount) {
        if(i >= cmd.commands.size)
            comp.addExtra(data.msg("\n"))

        else {
            val char = chars[if (i == commandsThisPage) 2 else 0]
            val subName = cmd.commands[i].name
            val args = cmd.commands[i].arguments

            if (args == "") comp.append(TextComponent(data.msg("  %r$char %p$subName %r- ${cmd.commands[i].description}\n"))
                    .setHoverText(data.msg("%rRun command /%p$name$subCmdArg $subName", false))
                    .setCommand("/$name$subCmdArg $subName"))

            //  Return default
            else comp.append(TextComponent(data.msg("  %r$char %p$subName %s$args %r- ${cmd.commands[i].description}\n"))
                    .setHoverText(data.msg("%rFill /%p$name$subCmdArg $subName %s${args}", false))
                    .setSuggestion("/$name$subCmdArg $subName"))
        }
    }

    //  Build the footer of the message

    comp.addExtra(data.msg("&m---------------------------------------------"))

    //  Send the message

    sender.spigot().sendMessage(comp)
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

    sender.spigot().sendMessage(TextComponent(data.msg("Subcommand %p$subCmd %rdoes not exist!\n$prefix Try /%p"))
        .append(TextComponent("$name $b")
            .setHoverText(data.msg("%rRun command /%p$name $b", false))
            .setCommand("/$name $b")
        )
    )
}