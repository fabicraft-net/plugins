package net.fabicraft.velocity.command.commands;

import com.velocitypowered.api.command.CommandSource;
import net.fabicraft.velocity.FabiCraftVelocity;
import net.fabicraft.velocity.command.VelocityCommand;
import org.incendo.cloud.context.CommandContext;

public final class FabiCraftVelocityCommand extends VelocityCommand {
	private static final String PERMISSION_RELOAD = "fabicraft.velocity.command.fabicraftvelocity.reload";

	public FabiCraftVelocityCommand(FabiCraftVelocity plugin) {
		super(plugin);
	}

	@Override
	public void register() {
		var builder = super.manager.commandBuilder("fabicraftvelocity");
		super.manager.command(builder.literal("reload").permission(PERMISSION_RELOAD).handler(this::handleReload));
	}

	private void handleReload(CommandContext<CommandSource> context) {
		super.plugin.load();
	}
}
