package net.fabicraft.survival.listener;

import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent;
import net.fabicraft.survival.FabiCraftSurvival;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class PlayerListener implements Listener {
	private final FabiCraftSurvival plugin;

	public PlayerListener(FabiCraftSurvival plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onSpawnLocation(AsyncPlayerSpawnLocationEvent event) {
		if (!event.isNewPlayer()) {
			return;
		}

		Location spawnLocation = this.plugin.firstSpawnManager().location();
		event.setSpawnLocation(spawnLocation);
	}
}
