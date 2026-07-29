package net.fabicraft.paper.bedwars.locale;

import net.kyori.adventure.key.Key;
import org.slf4j.Logger;

public final class BedwarsTranslationManager extends net.fabicraft.common.locale.TranslationManager {
	public BedwarsTranslationManager(Logger logger) {
		super(logger);
	}

	@Override
	protected Key name() {
		return Key.key("fabicraft", "bedwars");
	}
}
