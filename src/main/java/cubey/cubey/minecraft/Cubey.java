package cubey.cubey.minecraft;

import cubey.cubey.general.Recipie;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Cubey implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args[0] == "compile") {
            if (args.length > 1) {


            }
        }
        return true;
    }
}
