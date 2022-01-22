package de.funboyy.challenge.command;

import de.funboyy.challenge.Config;
import de.funboyy.challenge.SneakyEntityPlugin;
import javax.annotation.Nonnull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChallengeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull final CommandSender sender, @Nonnull final Command command,
                             @Nonnull final String label, @Nonnull final String[] args) {

        if (!sender.hasPermission("sneakyentity.command.challenge")) {
            sender.sendMessage(Config.getInstance().getNoPermission());
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(Config.getInstance().getChallengeHelp());
            return true;
        }

        if (args[0].equalsIgnoreCase("start")) {
            start(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("stop")) {
            stop(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("toggleFail")) {
            toggleFail(sender);
            return true;
        }

        sender.sendMessage(Config.getInstance().getChallengeHelp());
        return true;
    }

    private void start(final CommandSender sender) {
        if (!sender.hasPermission("sneakyentity.command.challenge.start")) {
            sender.sendMessage(Config.getInstance().getNoPermission());
            return;
        }

        if (SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            sender.sendMessage(Config.getInstance().getChallengeAlreadyFinished());
            return;
        }

        if (SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            sender.sendMessage(Config.getInstance().getChallengeAlreadyRunning());
            return;
        }

        SneakyEntityPlugin.getInstance().getTimer().start();
        sender.sendMessage(Config.getInstance().getChallengeStart());
    }

    private void stop(final CommandSender sender) {
        if (!sender.hasPermission("sneakyentity.command.challenge.stop")) {
            sender.sendMessage(Config.getInstance().getNoPermission());
            return;
        }

        if (SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            sender.sendMessage(Config.getInstance().getChallengeAlreadyFinished());
            return;
        }

        if (!SneakyEntityPlugin.getInstance().getTimer().isRunning()) {
            sender.sendMessage(Config.getInstance().getChallengeAlreadyPaused());
            return;
        }

        SneakyEntityPlugin.getInstance().getTimer().stop();
        sender.sendMessage(Config.getInstance().getChallengePause());
    }

    private void toggleFail(final CommandSender sender) {
        if (!sender.hasPermission("sneakyentity.command.challenge.togglefail")) {
            sender.sendMessage(Config.getInstance().getNoPermission());
            return;
        }

        if (SneakyEntityPlugin.getInstance().getTimer().isFinished()) {
            sender.sendMessage(Config.getInstance().getChallengeAlreadyFinished());
            return;
        }

        final boolean fail = !Config.getInstance().failOnDeath();
        Config.getInstance().setFailOnDeath(fail);

        if (fail) {
            sender.sendMessage(Config.getInstance().getChallengeToggleEnable());
            return;
        }

        sender.sendMessage(Config.getInstance().getChallengeToggleDisable());
    }

}
