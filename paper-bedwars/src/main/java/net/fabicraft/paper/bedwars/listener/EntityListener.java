package net.fabicraft.paper.bedwars.listener;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public final class EntityListener implements Listener {
	@EventHandler
	public void onFireballExplode(EntityExplodeEvent event) {
		if (event.getEntityType() == EntityType.FIREBALL) {
			event.blockList().removeIf(block -> block.getType() == Material.END_STONE);
		}
	}
}
