package net.serahill.minereignItems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.serahill.minereignItems.MinereignItems.plugin;

public class MinereignCommand implements CommandExecutor {
    private final CraftingRecipeManager recipeManager;
    public MinereignCommand(CraftingRecipeManager recipeManager) {
        this.recipeManager = recipeManager;
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
