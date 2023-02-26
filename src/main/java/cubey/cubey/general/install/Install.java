package cubey.cubey.general.install;

import cubey.cubey.minecraft.Cubey;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Install {
    public void install() throws IOException {
        Cubey plugin = Cubey.getInstance();
        File dataFile = new File(plugin.getDataFolder() +  File.separator);
        File projects = new File(dataFile + File.separator + "projects" +  File.separator);
        Bukkit.getConsoleSender().sendMessage(plugin.getDataFolder() + File.separator);

        if(!dataFile.exists())
            try { dataFile.mkdir(); } catch (Exception e) { e.printStackTrace(); }
        if(!projects.exists())
            try { projects.mkdir(); } catch (Exception e) { e.printStackTrace(); }
    }
}
