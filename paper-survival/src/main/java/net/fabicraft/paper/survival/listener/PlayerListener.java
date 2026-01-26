package net.fabicraft.paper.survival.listener;

import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent;
import net.fabicraft.paper.survival.FabiCraftPaperSurvival;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class PlayerListener implements Listener {
	private final FabiCraftPaperSurvival plugin;

	public PlayerListener(FabiCraftPaperSurvival plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onSpawnLocation(AsyncPlayerSpawnLocationEvent event) {
		if (!event.isNewPlayer()) {
			return;
		}

		Location spawnLocation = this.plugin.firstSpawnManager().location();
		if (spawnLocation == null) {
			return;
		}
		event.setSpawnLocation(spawnLocation);
	}
}
