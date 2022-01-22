package de.funboyy.challenge.listener;

import de.funboyy.challenge.Config;
import de.funboyy.challenge.SneakyEntityPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ChallengeListener implements Listener {

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            return;
        }

        if (!Config.getInstance().failOnDeath()) {
            return;
        }

        SneakyEntityPlugin.getInstance().getTimer().stop();
        SneakyEntityPlugin.getInstance().getTimer().setFinished(true);

        final String reason = event.getDeathMessage();
        Bukkit.getScheduler().runTaskLater(SneakyEntityPlugin.getInstance(), () -> {
            event.getEntity().spigot().respawn();

            Bukkit.getOnlinePlayers().forEach(player -> player.setGameMode(GameMode.SPECTATOR));

            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(Config.getInstance().getDeathMessage()
                    .replace("%deathMessage%", reason == null ? "error" : reason));
            Bukkit.broadcastMessage(Config.getInstance().getDeathFinished());
            Bukkit.broadcastMessage(Config.getInstance().getDeathTime()
                    .replace("%duration%", SneakyEntityPlugin.getInstance().getTimer().toString()));
            Bukkit.broadcastMessage("");
        }, 2);

        event.setDeathMessage(null);
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent event) {
        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            return;
        }

        if (!(event.getEntity() instanceof EnderDragon)) {
            return;
        }

        SneakyEntityPlugin.getInstance().getTimer().stop();
        SneakyEntityPlugin.getInstance().getTimer().setFinished(true);

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(Config.getInstance().getKillMessage());
        Bukkit.broadcastMessage(Config.getInstance().getKillFinished());
        Bukkit.broadcastMessage(Config.getInstance().getKillTime()
                .replace("%duration%", SneakyEntityPlugin.getInstance().getTimer().toString()));
        Bukkit.broadcastMessage("");
    }

}
