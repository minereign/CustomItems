package net.serahill.minereignItems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.joml.Matrix2dc;
import net.serahill.minereignItems.CustomItem;

import java.util.function.Supplier;

import static net.serahill.minereignItems.MinereignItems.plugin;
import static org.bukkit.Bukkit.getLogger;

public class MinereignCommand implements CommandExecutor {
    private final CraftingRecipeManager recipeManager;
    private final ItemManager itemManager;

    public MinereignCommand(CraftingRecipeManager recipeManager, ItemManager itemManager) {
        this.recipeManager = recipeManager;
        this.itemManager = itemManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("minereign")) {
            if (args.length == 0) {
                sender.sendMessage("Cool message");
                return true;
            }

            switch (args[0]) {
                case "reload": {
                    if (sender.hasPermission("minereign.reload")) {
                        recipeManager.unregisterRecipes();
                        plugin.reloadConfig();
                        recipeManager.loadCustomRecipes();
                        itemManager.reloadCustomItems();
                        sender.sendMessage("Configuration reloaded.");
                        return true;
                    }
                    break;
                }
                case "give": {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (sender.hasPermission("minereign.give")) {
                            if (args.length == 2) {
                                ConfigurationSection section = itemManager.customItems.get(args[1]);
                                if (section == null) return false;
                                ItemStack item = CustomItem.CreateItem(section);
                                player.getInventory().addItem(item);
                            }
                        }
                        break;
                    }
                }
                default:
                    sender.sendMessage("&cThis either has not been added yet or you have no permission.");
                    break;
            }
        }
        return false;
    }
}
