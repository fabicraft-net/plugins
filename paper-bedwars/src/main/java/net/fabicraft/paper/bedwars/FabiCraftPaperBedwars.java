package net.fabicraft.paper.bedwars;

import de.marcely.bedwars.api.GameAPI;
import net.fabicraft.paper.bedwars.shop.FabiCraftShopLayout;
import org.bukkit.plugin.java.JavaPlugin;

public final class FabiCraftPaperBedwars extends JavaPlugin {

	@Override
	public void onEnable() {
		GameAPI.get().registerShopLayout(new FabiCraftShopLayout(this));
	}
}
