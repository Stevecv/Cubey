package com.jeroenvdg.rounds.command.util.builders

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent


fun TextComponent.color(color: ChatColor): TextComponent {
    this.color = color
    return this
}


fun TextComponent.bold(bold: Boolean): TextComponent {
    isBold = bold
    return this
}


fun TextComponent.italic(italic: Boolean): TextComponent {
    isItalic = italic
    return this
}


fun TextComponent.underlined(underlined: Boolean): TextComponent {
    isUnderlined = underlined
    return this
}


fun TextComponent.strikethrough(strikethrough: Boolean): TextComponent {
    isStrikethrough = strikethrough
    return this
}


fun TextComponent.obfuscated(obfuscated: Boolean): TextComponent {
    isObfuscated = obfuscated
    return this
}


fun TextComponent.setCommand(command: String): TextComponent {
    clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, command)
    return this
}


fun TextComponent.setSuggestion(command: String): TextComponent {
    clickEvent = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command)
    return this
}


fun TextComponent.setHoverText(text: TextComponent): TextComponent {
    hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, listOf(text).toTypedArray())
    return this
}


fun TextComponent.setHoverText(text: String): TextComponent {
    return setHoverText(TextComponent(text))
}


fun TextComponent.append(text: String): TextComponent {
    addExtra(text)
    return this
}


fun TextComponent.append(text: TextComponent): TextComponent {
    addExtra(text)
    return this
}