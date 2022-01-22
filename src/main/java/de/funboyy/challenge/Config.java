package de.funboyy.challenge;

import javax.annotation.Nullable;
import net.md_5.bungee.api.ChatColor;

public class Config {

    private static Config instance;

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }


    private String replace(@Nullable final String message) {
        if (message == null) {
            throw new IllegalArgumentException("Config cannot be null");
        }

        return ChatColor.translateAlternateColorCodes('&', message.replace("%prefix%", getPrefix()));
    }

    public boolean failOnDeath() {
        return SneakyEntityPlugin.getInstance().getConfig().getBoolean("failOnDeath");
    }

    public void setFailOnDeath(final boolean failOnDeath) {
        SneakyEntityPlugin.getInstance().getConfig().set("failOnDeath", failOnDeath);
        SneakyEntityPlugin.getInstance().saveConfig();
    }


    private String getPrefix() {
        return SneakyEntityPlugin.getInstance().getConfig().getString("prefix");
    }

    public String getNoPermission() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.no-permission"));
    }


    public String getChallengeHelp() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.challenge.usage"));
    }

    public String getChallengeAlreadyRunning() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.challenge.already.running"));
    }

    public String getChallengeAlreadyPaused() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.challenge.already.paused"));
    }

    public String getChallengeAlreadyFinished() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.challenge.already.finished"));
    }

    public String getChallengeStart() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.challenge.start"));
    }

    public String getChallengePause() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.challenge.pause"));
    }

    public String getChallengeToggleEnable() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.challenge.toggleFail.enabled"));
    }

    public String getChallengeToggleDisable() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.command.challenge.toggleFail.disabled"));
    }


    public String getDeathMessage() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.failed.reason"));
    }

    public String getDeathFinished() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.failed.message"));
    }

    public String getDeathTime() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.failed.duration"));
    }


    public String getKillMessage() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.completed.reason"));
    }

    public String getKillFinished() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.completed.message"));
    }

    public String getKillTime() {
        return replace(SneakyEntityPlugin.getInstance().getConfig().getString("message.completed.duration"));
    }

}
