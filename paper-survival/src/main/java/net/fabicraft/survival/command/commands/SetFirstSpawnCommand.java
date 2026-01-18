package net.fabicraft.survival.command.commands;

import net.fabicraft.common.locale.Components;
import net.fabicraft.common.locale.MessageType;
import net.fabicraft.survival.FabiCraftSurvival;
import net.fabicraft.survival.command.PaperSurvivalCommand;
import net.kyori.adventure.text.TranslatableComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.paper.util.sender.PlayerSource;

public final class SetFirstSpawnCommand extends PaperSurvivalCommand {
	private static final String PERMISSION = "fabicraft.survival.command.setfirstspawn";
	private static final TranslatableComponent COMPONENT = Components.translatable("fabicraft.survival.command.setfirstspawn", MessageType.SUCCESS);

	public SetFirstSpawnCommand(FabiCraftSurvival plugin) {
		super(plugin);
	}

	@Override
	public void register() {
		var builder = super.manager.commandBuilder("setfirstspawn").permission(PERMISSION).senderType(PlayerSource.class).handler(this::handle);
	}

	private void handle(CommandContext<PlayerSource> ctx) {
		Player player = ctx.sender().source();
		Location spawnLocation = player.getLocation();

		this.plugin.firstSpawnManager().location(spawnLocation);
		spawnLocation.getWorld().setSpawnLocation(spawnLocation); // Also set the world spawn just in case

		player.sendMessage(COMPONENT);
	}
}
