package net.serahill.minereignItems;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinereignItems extends JavaPlugin {

    public static MinereignItems plugin;
    private CraftingRecipeManager RecipeManager;
    @Override
    public void onEnable() {
        plugin = this;

        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        this.RecipeManager = new CraftingRecipeManager(this);
    }
}
