package net.fabicraft.survival.command;

import net.fabicraft.survival.FabiCraftSurvival;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.paper.util.sender.Source;

public abstract class PaperSurvivalCommand {
	protected final PaperCommandManager<Source> manager;
	protected final FabiCraftSurvival plugin;

	public PaperSurvivalCommand(FabiCraftSurvival plugin) {
		this.plugin = plugin;
		this.manager = plugin.commandManager();
	}

	public abstract void register();
}
