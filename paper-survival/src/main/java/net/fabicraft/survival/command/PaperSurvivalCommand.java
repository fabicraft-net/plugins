package net.fabicraft.survival.command;

import net.fabicraft.common.locale.Components;
import net.fabicraft.common.locale.MessageType;
import net.fabicraft.survival.FabiCraftSurvival;
import net.kyori.adventure.text.TranslatableComponent;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.paper.util.sender.Source;

public abstract class PaperSurvivalCommand {
	protected final PaperCommandManager<Source> manager;
	protected final FabiCraftSurvival plugin;
	protected static final TranslatableComponent COMPONENT_EXCEPTION_UNEXPECTED = Components.translatable(
			"fabicraft.common.command.exception.unexpected",
			MessageType.ERROR
	);

	public PaperSurvivalCommand(FabiCraftSurvival plugin) {
		this.plugin = plugin;
		this.manager = plugin.commandManager();
	}

	public abstract void register();
}
