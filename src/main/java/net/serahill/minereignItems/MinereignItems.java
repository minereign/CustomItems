package net.serahill.minereignItems;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinereignItems extends JavaPlugin {

    public static MinereignItems plugin;
    private CraftingRecipeManager RecipeManager;
    private ItemManager ItemManager;

    @Override
    public void onEnable() {
        plugin = this;

        this.RecipeManager = new CraftingRecipeManager();
        this.ItemManager = new ItemManager();

        this.getCommand("minereign").setExecutor(new MinereignCommand(this.RecipeManager, this.ItemManager));

        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

}
