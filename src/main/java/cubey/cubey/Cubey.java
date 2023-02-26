package cubey.cubey;

import cubey.cubey.general.install.Install;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Cubey extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        try { new Install().install(); } catch (IOException e) { throw new RuntimeException(e); }
        Bukkit.getConsoleSender().sendMessage("Loaded Cubey");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
