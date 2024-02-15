package net.serahill.rareore;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomItems extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }
}
