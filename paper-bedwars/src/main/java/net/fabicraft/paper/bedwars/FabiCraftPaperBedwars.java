package net.fabicraft.paper.bedwars;

import de.marcely.bedwars.api.GameAPI;
import net.fabicraft.paper.bedwars.command.SpawnCommand;
import net.fabicraft.paper.bedwars.listener.EntityListener;
import net.fabicraft.paper.bedwars.locale.BedwarsTranslationManager;
import net.fabicraft.paper.bedwars.shop.FabiCraftShopLayout;
import net.fabicraft.paper.common.command.CommandManagerProvider;
import net.fabicraft.paper.common.command.PaperCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.paper.util.sender.Source;

import java.util.List;

public final class FabiCraftPaperBedwars extends JavaPlugin {
	private GameAPI api;
	private PaperCommandManager<Source> commandManager;

	public FabiCraftPaperBedwars() {
		new BedwarsTranslationManager(getSLF4JLogger());
	}

	@Override
	public void onEnable() {
		this.api = GameAPI.get();
		this.api.registerShopLayout(new FabiCraftShopLayout(this));
		this.commandManager = new CommandManagerProvider().manager(this);
		registerCommands();
		registerListeners();
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

	private void registerListeners() {
		PluginManager manager = getServer().getPluginManager();
		List.of(
				new EntityListener()
		).forEach(listener -> manager.registerEvents(listener, this));
	}
}
