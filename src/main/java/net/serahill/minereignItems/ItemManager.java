package net.serahill.minereignItems;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.HashMap;

import static net.serahill.minereignItems.MinereignItems.plugin;
import static org.bukkit.Bukkit.getLogger;

public class ItemManager {

    public HashMap<String, ConfigurationSection> customItems;

    public ItemManager() {
        this.customItems = loadDataSectionsFromConfigs("items");
    }

    public HashMap<String, ConfigurationSection> loadDataSectionsFromConfigs(String subdirectoryName) {
        HashMap<String, ConfigurationSection> dataMap = new HashMap<>();
        File subdirectory = new File(plugin.getDataFolder(), subdirectoryName);

        if (subdirectory.exists() && subdirectory.isDirectory()) {
            File[] files = subdirectory.listFiles((dir, name) -> name.endsWith(".yml"));
            if (files != null) {
                for (File file : files) {
                    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                    if (config.contains("data")) {
                        String fileNameWithoutExtension = file.getName().replaceFirst("[.][^.]+$", "");
                        dataMap.put(fileNameWithoutExtension, config.getConfigurationSection("data"));
                        getLogger().info("Loaded data from " + file.getName());
                        customItems = dataMap;
                    }
                }
            }
        } else {
            System.out.println("The specified subdirectory does not exist or is not a directory.");
        }

        return dataMap;
    }

    public void reloadCustomItems() {
        this.customItems = loadDataSectionsFromConfigs("items");
    }

}
