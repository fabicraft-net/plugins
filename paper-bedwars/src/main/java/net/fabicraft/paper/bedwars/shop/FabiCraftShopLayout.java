package net.fabicraft.paper.bedwars.shop;

import de.marcely.bedwars.api.game.shop.ShopItem;
import de.marcely.bedwars.api.game.shop.ShopPage;
import de.marcely.bedwars.api.game.shop.layout.ShopLayout;
import de.marcely.bedwars.api.game.shop.layout.ShopLayoutHandler;
import de.marcely.bedwars.api.game.shop.price.ShopPrice;
import de.marcely.bedwars.api.message.Message;
import de.marcely.bedwars.tools.gui.CenterFormat;
import de.marcely.bedwars.tools.gui.GUI;
import de.marcely.bedwars.tools.gui.type.ChestGUI;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemLore;
import net.fabicraft.common.locale.BrandColor;
import net.fabicraft.paper.bedwars.FabiCraftPaperBedwars;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public final class FabiCraftShopLayout implements ShopLayoutHandler, ShopLayout {
	private final FabiCraftPaperBedwars plugin;
	private final LegacyComponentSerializer legacyComponentSerializer = LegacyComponentSerializer.legacySection();
	private final MiniMessage miniMessage = MiniMessage.miniMessage();

	public FabiCraftShopLayout(FabiCraftPaperBedwars plugin) {
		this.plugin = plugin;
	}

	@Override
	public GUI build(OpenEvent event) {
		final Player player = event.getPlayer();
		final ChestGUI gui = new ChestGUI(3, event.getSituationalGUITitle());

		int maxItemCount = 0;
		for (ShopPage page : event.getPages()) {
			gui.addItem(event.build(page));

			// Track max page size
			int items = page.getItems().size();
			if (items > maxItemCount) {
				maxItemCount = items;
			}
		}

		if (event.getOpenPage() == null) {
			event.setOpenPage(event.getPages().getFirst());
		}

		gui.setHeight(2 + (maxItemCount + 8) / 9);

		var pageItems = event.getPageItems();
		for (int i = 0; i < pageItems.size(); i++) {
			ShopItem item = pageItems.get(i);
			ItemStack icon = item.getDynamicIcon().get(player);
			icon.setData(DataComponentTypes.ITEM_NAME, componentFromLegacy(item.getDisplayName(player)));

			List<Component> loreLines = new ArrayList<>();
			String description = item.getConfigDescription();
			if (description != null) {
				loreLines.add(this.miniMessage.deserialize(description, player));
			}

			loreLines.add(Component.empty());

			boolean canBuy = true;
			for (ShopPrice price : item.getPrices()) {
				int amount = price.getAmount(player);
				loreLines.add(loreComponent(componentFromLegacy("§f" + amount + " " + price.getDisplayName(player, amount))));
				if (price.getMissingAmount(player) > 0) {
					canBuy = false;
				}
			}

			loreLines.add(Component.empty());

			Message canBuyMessage = canBuy ? Message.buildByKey("shop_fabicraft_clicktobuy") : Message.buildByKey("shop_fabicraft_missingmaterials");
			loreLines.add(loreComponent(this.miniMessage.deserialize(canBuyMessage.getRawMessage(player.locale().toString()), player)));

			icon.setData(DataComponentTypes.LORE, ItemLore.lore().addLines(loreLines));
			item.setIcon(icon);
			gui.setItem(event.build(item), 18 + i);
		}

		gui.formatAnyRow(CenterFormat.CENTRALIZED_EVEN);

		return gui;
	}

	@Override
	public String getName() {
		return "FabiCraft";
	}

	@Override
	public boolean isBeta() {
		return false;
	}

	@Override
	public Plugin getPlugin() {
		return this.plugin;
	}

	@Override
	public ShopLayoutHandler getHandler() {
		return this;
	}

	private Component componentFromLegacy(String legacyString) {
		return this.legacyComponentSerializer.deserialize(legacyString);
	}

	private Component loreComponent(Component component) {
		return component
				.colorIfAbsent(BrandColor.GRAY.textColor)
				.decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
	}
}
