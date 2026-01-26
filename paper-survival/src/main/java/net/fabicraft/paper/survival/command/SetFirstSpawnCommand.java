package net.fabicraft.paper.survival.command;

import net.fabicraft.common.locale.Components;
import net.fabicraft.common.locale.MessageType;
import net.fabicraft.paper.common.command.PaperCommand;
import net.fabicraft.paper.survival.FabiCraftPaperSurvival;
import net.fabicraft.paper.survival.spawn.FirstSpawnManager;
import net.kyori.adventure.text.TranslatableComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.paper.util.sender.PlayerSource;

import java.io.IOException;

public final class SetFirstSpawnCommand extends PaperCommand<FabiCraftPaperSurvival> {
	private static final String PERMISSION = "fabicraft.survival.command.setfirstspawn";
	private static final TranslatableComponent COMPONENT = Components.translatable("fabicraft.survival.command.setfirstspawn", MessageType.SUCCESS);
	private final FirstSpawnManager firstSpawnManager;

	public SetFirstSpawnCommand(FabiCraftPaperSurvival plugin) {
		super(plugin, plugin.commandManager());
		this.firstSpawnManager = new FirstSpawnManager(plugin);
	}

	@Override
	public void register() {
		var builder = super.manager.commandBuilder("setfirstspawn").permission(PERMISSION).senderType(PlayerSource.class).handler(this::handle);
		super.manager.command(builder);
	}

	private void handle(CommandContext<PlayerSource> ctx) {
		Player player = ctx.sender().source();
		Location spawnLocation = player.getLocation();

		this.firstSpawnManager.location(spawnLocation);

		try {
			this.firstSpawnManager.save();
			player.sendMessage(COMPONENT);
		} catch (IOException e) {
			super.plugin.getSLF4JLogger().error("Could not save first spawn location", e);
			player.sendMessage(COMPONENT_EXCEPTION_UNEXPECTED);
		}


	}
}
