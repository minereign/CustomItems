package net.serahill.minereignItems;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinereignItems extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }
}