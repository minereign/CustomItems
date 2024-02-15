package net.serahill.customItems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CraftingRecipeManager {
    FileConfiguration config;
    CustomItems plugin;

    public CraftingRecipeManager(CustomItems plugin) {
        config = plugin.getConfig();
    }

    public void loadCustomRecipes() {
        for (String key : config.getConfigurationSection("recipes.shaped").getKeys(false)) {
            ItemStack itemResult = new ItemStack(Material.valueOf(config.getString("recipes.shaped." + key + ".result")), config.getInt("recipes.shaped." + key + ".amount"));
            NamespacedKey recipeKey = new NamespacedKey(plugin, key);
            ShapedRecipe recipe = new ShapedRecipe(recipeKey, itemResult);

            List<String> shape = config.getStringList("recipes.shaped." + key + ".shape");
            recipe.shape(shape.toArray(new String[0]));

            ConfigurationSection ingredients = config.getConfigurationSection("recipes.shaped." + key + ".ingredients");
            for (String ingredientKey : ingredients.getKeys(false)) {
                Material mat = Material.valueOf(ingredients.getString(ingredientKey));
                recipe.setIngredient(ingredientKey.charAt(0), mat);
            }

            Bukkit.addRecipe(recipe);
        }

        for (String key : config.getConfigurationSection("recipes.shapeless").getKeys(false)) {
            ItemStack itemResult = new ItemStack(Material.valueOf(config.getString("recipes.shapeless." + key + ".result")), config.getInt("recipes.shapeless." + key + ".amount"));
            NamespacedKey recipeKey = new NamespacedKey(plugin, key);
            ShapelessRecipe recipe = new ShapelessRecipe(recipeKey, itemResult);

            List<String> ingredients = config.getStringList("recipes.shapeless." + key + ".ingredients");
            for (String ingredient : ingredients) {
                recipe.addIngredient(Material.valueOf(ingredient));
            }

            Bukkit.addRecipe(recipe);
        }


    }

    private ItemStack createCustomItem(String key) {
        if (!config.contains("custom_items." + key)) {
            return null; // Custom item definition not found
        }

        Material material = Material.valueOf(config.getString("custom_items." + key + ".material"));
        int customModelData = config.getInt("custom_items." + key + ".custom_model_data");
        String name = config.getString("custom_items." + key + ".name");
        List<String> lore = config.getStringList("custom_items." + key + ".lore");

        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(customModelData);
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        return item;
    }

}
