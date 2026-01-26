package net.fabicraft.paper.common.command;

import net.fabicraft.common.locale.Components;
import net.fabicraft.common.locale.MessageType;
import net.kyori.adventure.text.TranslatableComponent;
import org.incendo.cloud.paper.PaperCommandManager;
import org.incendo.cloud.paper.util.sender.Source;

public abstract class PaperCommand<P> {
	protected static final TranslatableComponent COMPONENT_EXCEPTION_UNEXPECTED = Components.translatable(
			"fabicraft.common.command.exception.unexpected",
			MessageType.ERROR
	);
	protected final PaperCommandManager<Source> manager;
	protected final P plugin;

	public PaperCommand(P plugin, PaperCommandManager<Source> manager) {
		this.plugin = plugin;
		this.manager = manager;
	}

	public abstract void register();
}
