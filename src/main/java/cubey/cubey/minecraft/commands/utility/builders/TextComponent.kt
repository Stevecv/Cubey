package cubey.cubey.minecraft.commands.utility.builders

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import org.bukkit.ChatColor



fun TextComponent.Builder.setHoverText(text: String): TextComponent.Builder {
//    return setHoverText(TextComponent(text))
    return this;
}


fun TextComponent.Builder.append(text: String): TextComponent.Builder {
    append(Component.text(text));
    return this
}