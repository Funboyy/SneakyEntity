package de.funboyy.challenge.listener;

import de.funboyy.challenge.SneakyEntityPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning() &&
                !SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(final BlockBreakEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning() &&
                !SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(final BlockPlaceEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning() &&
                !SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(final PlayerDropItemEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning() &&
                !SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickUp(final PlayerPickupItemEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning() &&
                !SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(final FoodLevelChangeEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            event.setFoodLevel(20);
        }
    }

    @EventHandler
    public void onDamage(final EntityDamageEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning() &&
                !SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(final EntityExplodeEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockExplode(final BlockExplodeEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning() &&
                !SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            final Player player = event.getPlayer();

            if (event.getTo() == null) {
                return;
            }

            if (event.getFrom().getBlockX() == event.getTo().getBlockX() &&
                    event.getFrom().getBlockZ() == event.getTo().getBlockZ()) {
                return;
            }

            player.teleport(event.getFrom());
        }
    }

}
