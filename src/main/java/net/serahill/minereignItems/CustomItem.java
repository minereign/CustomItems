package net.serahill.minereignItems;


import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.serahill.minereignItems.ItemManager;

import static net.serahill.minereignItems.MinereignItems.plugin;
import static org.bukkit.Bukkit.getLogger;

public class CustomItem {

    public static ItemStack CreateItem(ConfigurationSection config) {

        if (!config.isSet("type")) {
            getLogger().warning("The item config " + config + " is not set in the config2.");
        }

        String type = config.isSet("type") ? config.getString("type") : "STONE";
        int amount = config.isSet( "amount") ? config.getInt("amount") : 1;

        ItemStack item = new ItemStack(Material.valueOf(type.toUpperCase()), amount);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (config.isSet("name")) {
                String name = ChatColor.translateAlternateColorCodes('&', config.getString("name"));
                meta.setDisplayName(name);
            }

            if (config.isSet("lore")) {
                List<String> lore = new ArrayList<>();
                for (String line : config.getStringList("lore")) {
                    lore.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                meta.setLore(lore);
            }

            if (config.isSet("enchantments")) {
                config.getConfigurationSection("enchantments").getKeys(false).forEach(key -> {
                    Enchantment enchantment = Enchantment.getByName(key.toUpperCase());
                    if (enchantment != null) {
                        int level = config.getInt("enchantments." + key);
                        meta.addEnchant(enchantment, level, true);
                    }
                });
            }

            if (config.isSet("damage")) {
                if (meta instanceof Damageable) {
                    ((Damageable) meta).setDamage(config.getInt("damage"));
                }
            }

            if (config.isSet("custom_model_data")) {
                meta.setCustomModelData(config.getInt("custom_model_data"));
            }

            if (config.isSet("unbreakable")) {
                if (config.getBoolean("unbreakable")) {
                    meta.setUnbreakable(true);
                } else {
                    meta.setUnbreakable(false);
                }
            }

            item.setItemMeta(meta);
        }
        return item;
    }

}
