package cubey.cubey.minecraft;

import cubey.cubey.general.install.Install;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public final class Cubey extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            new Install().install();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
