package net.serahill.minereignItems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MinereignCommand implements CommandExecutor {
    private JavaPlugin plugin;
    public MinereignCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("minereign")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("minereign.reload")) {
                plugin.reloadConfig();
                sender.sendMessage("Configuration reloaded.");
                return true;
            }
            sender.sendMessage("&cThis either has not been added yet or you have no permission.");
        }
        return false;
    }
}
