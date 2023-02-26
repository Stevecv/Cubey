package cubey.cubey.minecraft;

import cubey.cubey.general.install.Install;
import cubey.cubey.minecraft.commands.utility.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public final class Cubey extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            new Install().install();
        } catch (IOException e) {throw new RuntimeException(e);}
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
