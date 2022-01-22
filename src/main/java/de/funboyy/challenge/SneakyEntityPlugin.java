package de.funboyy.challenge;

import de.funboyy.challenge.command.ChallengeCommand;
import de.funboyy.challenge.listener.ChallengeListener;
import de.funboyy.challenge.listener.ProtectionListener;
import de.funboyy.challenge.listener.SneakyEntityListener;
import de.funboyy.challenge.utils.Timer;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SneakyEntityPlugin extends JavaPlugin {

    public static SneakyEntityPlugin getInstance() {
        return getPlugin(SneakyEntityPlugin.class);
    }

    @Getter private Timer timer;

    @Override
    public void onEnable() {
        loadConfig();

        this.timer = new Timer();
        timer();

        Bukkit.getPluginManager().registerEvents(new ChallengeListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new SneakyEntityListener(), this);

        final PluginCommand command = getCommand("challenge");
        if (command != null) {
            command.setExecutor(new ChallengeCommand());
        }
    }

    private void loadConfig() {
        this.saveDefaultConfig();

        this.getConfig().options().copyDefaults(true);
        this.reloadConfig();
    }

    private void timer() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> Bukkit.getOnlinePlayers().forEach(player -> {
            final TextComponent text = new TextComponent(this.timer.toString());
            text.setColor(ChatColor.GOLD);
            text.setBold(true);

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
        }), 0, 10);
    }
}
