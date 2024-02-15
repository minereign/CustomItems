package net.serahill.minereignItems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MinereignCommand implements CommandExecutor {
    private final CraftingRecipeManager recipeManager;
    private JavaPlugin plugin;
    public MinereignCommand(MinereignItems plugin) {
        this.plugin = plugin;
        this.recipeManager = new CraftingRecipeManager(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("minereign")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("minereign.reload")) {
                recipeManager.unregisterRecipes();
                plugin.reloadConfig();
                recipeManager.loadCustomRecipes();
                sender.sendMessage("Configuration reloaded.");
                return true;
            }
            sender.sendMessage("&cThis either has not been added yet or you have no permission.");
        }
        return false;
    }
}
