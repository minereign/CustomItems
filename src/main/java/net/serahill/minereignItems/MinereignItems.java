package net.serahill.minereignItems;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinereignItems extends JavaPlugin {

    public static MinereignItems plugin;
    private CraftingRecipeManager RecipeManager;
    @Override
    public void onEnable() {
        plugin = this;

        this.RecipeManager = new CraftingRecipeManager();

        this.getCommand("minereign").setExecutor(new MinereignCommand(this.RecipeManager));

        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }
}
