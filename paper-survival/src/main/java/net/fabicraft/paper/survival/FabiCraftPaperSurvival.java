package net.fabicraft.paper.survival;

import net.fabicraft.common.command.TranslatableCaptionProvider;
import net.fabicraft.common.command.exception.ExceptionHandlers;
import net.fabicraft.paper.common.command.PaperCommand;
import net.fabicraft.paper.common.luckperms.PaperLuckPermsManager;
import net.fabicraft.paper.survival.command.RolePlayCommand;
import net.fabicraft.paper.survival.command.SetFirstSpawnCommand;
import net.fabicraft.paper.survival.listener.PlayerListener;
import net.fabicraft.paper.survival.locale.SurvivalTranslationManager;
import net.fabicraft.paper.survival.spawn.FirstSpawnManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.paper.util.sender.PaperSimpleSenderMapper;
import org.incendo.cloud.paper.util.sender.Source;

import java.util.List;

public final class FabiCraftPaperSurvival extends JavaPlugin {
	private FirstSpawnManager firstSpawnManager;
	private PaperCommandManager<Source> commandManager;
	private PaperLuckPermsManager luckPermsManager;

	@Override
	public void onEnable() {
		new SurvivalTranslationManager(getSLF4JLogger());

		this.firstSpawnManager = new FirstSpawnManager(this);
		this.firstSpawnManager.load();

		this.luckPermsManager = new PaperLuckPermsManager(getSLF4JLogger());

		setupCommandManager();
		registerCommands();

		registerListeners();
	}

	public FirstSpawnManager firstSpawnManager() {
		return this.firstSpawnManager;
	}

	public PaperCommandManager<Source> commandManager() {
		return this.commandManager;
	}

	public PaperLuckPermsManager luckPermsManager() {
		return this.luckPermsManager;
	}

	private void setupCommandManager() {
		PaperCommandManager<Source> commandManager = PaperCommandManager.builder(PaperSimpleSenderMapper.simpleSenderMapper())
				.executionCoordinator(ExecutionCoordinator.simpleCoordinator())
				.buildOnEnable(this);

		commandManager.captionRegistry().registerProvider(new TranslatableCaptionProvider<>());
		commandManager.exceptionController().clearHandlers();
		new ExceptionHandlers<Source>(getSLF4JLogger()).register(commandManager, Source::source);

		this.commandManager = commandManager;
	}

	private void registerListeners() {
		PluginManager manager = getServer().getPluginManager();
		List.of(
				new PlayerListener(this)
		).forEach(listener -> manager.registerEvents(listener, this));
	}

	private void registerCommands() {
		List.of(
				new SetFirstSpawnCommand(this),
				new RolePlayCommand(this)
		).forEach(PaperCommand::register);
	}
}
