package cubey.cubey.minecraft;

import cubey.cubey.minecraft.commands.utility.Command;
import org.bukkit.plugin.java.JavaPlugin;


public final class Cubey extends JavaPlugin {

    @Override
    public void onEnable() {
        addCommand(new cubey.cubey.minecraft.commands.Cubey());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void addCommand(Command command) {
        getServer().getPluginCommand(command.getData().getName()).setExecutor(command);
    }
}
