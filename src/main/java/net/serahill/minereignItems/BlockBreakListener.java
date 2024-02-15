package net.serahill.minereignItems;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class BlockBreakListener implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.DIAMOND_ORE) {
            if (random.nextInt(100) < 1) { // 1% chance
                ItemStack paper = new ItemStack(Material.PAPER, 1);
                ItemMeta meta = paper.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName("Treasure");
                    paper.setItemMeta(meta);
                }
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), paper);
            }
        }
    }
}