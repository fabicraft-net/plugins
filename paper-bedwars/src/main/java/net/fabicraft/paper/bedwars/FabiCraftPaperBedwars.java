package net.fabicraft.paper.bedwars;

import de.marcely.bedwars.api.GameAPI;
import net.fabicraft.paper.bedwars.command.SpawnCommand;
import net.fabicraft.paper.bedwars.shop.FabiCraftShopLayout;
import net.fabicraft.paper.common.command.CommandManagerProvider;
import net.fabicraft.paper.common.command.PaperCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.paper.util.sender.Source;

import java.util.List;

public final class FabiCraftPaperBedwars extends JavaPlugin {
	private final GameAPI api = GameAPI.get();
	private PaperCommandManager<Source> commandManager;

	@Override
	public void onEnable() {
		GameAPI.get().registerShopLayout(new FabiCraftShopLayout(this));
		this.commandManager = new CommandManagerProvider().manager(this);
		registerCommands();
	}

	public GameAPI api() {
		return this.api;
	}

	public PaperCommandManager<Source> commandManager() {
		return this.commandManager;
	}

	private void registerCommands() {
		List.of(
				new SpawnCommand(this)
		).forEach(PaperCommand::register);
	}
}
