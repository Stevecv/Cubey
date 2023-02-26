package cubey.cubey.minecraft;

<<<<<<< HEAD
import cubey.cubey.general.Recipie;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Cubey implements CommandExecutor {
=======
import cubey.cubey.general.install.Install;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Cubey extends JavaPlugin {

>>>>>>> Jeroen
    @Override
    public void onEnable() {
        // Plugin startup logic

<<<<<<< HEAD

            }
        }
        return true;
=======
        try { new Install().install(); } catch (IOException e) { throw new RuntimeException(e); }
        Bukkit.getConsoleSender().sendMessage("Loaded Cubey");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
>>>>>>> Jeroen
    }
}
