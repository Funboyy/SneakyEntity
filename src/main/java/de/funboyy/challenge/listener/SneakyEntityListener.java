package de.funboyy.challenge.listener;

import de.funboyy.challenge.SneakyEntityPlugin;
import de.funboyy.challenge.utils.RandomEntity;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakyEntityListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onSneak(final PlayerToggleSneakEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            return;
        }

        if (!event.isSneaking()) {
            return;
        }

        final Location location = event.getPlayer().getLocation();
        final EntityType type = RandomEntity.getInstance().getEntity();

        if (location.getWorld() == null) {
            return;
        }

        location.getWorld().spawnEntity(location, type);
    }

}
