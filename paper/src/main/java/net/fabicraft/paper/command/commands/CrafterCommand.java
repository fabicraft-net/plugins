package net.fabicraft.paper.command.commands;

import net.fabicraft.paper.FabiCraftPaper;
import net.fabicraft.paper.command.parser.CrafterMenuTypeParser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.paper.util.sender.PlayerSource;

public final class CrafterCommand extends net.fabicraft.paper.command.PaperCommand {
	private static final String PERMISSION = "sog.paper.command.crafter";

	public CrafterCommand(FabiCraftPaper plugin) {
		super(plugin);
	}

	@Override
	public void register() {
		var builder = super.manager.commandBuilder("crafter")
				.senderType(PlayerSource.class)
				.permission(PERMISSION);

		super.manager.command(builder
				.required("crafter", CrafterMenuTypeParser.menuTypeParser())
				.handler(this::handle)
		);
	}

	public void handle(CommandContext<PlayerSource> context) {
		Player player = context.sender().source();
		MenuType.Typed<?, ?> crafter = context.get("crafter");
		player.openInventory(crafter.create(player));
	}
}
