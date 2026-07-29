package net.fabicraft.paper.survival.locale;

import net.fabicraft.common.locale.TranslationManager;
import net.kyori.adventure.key.Key;
import org.slf4j.Logger;

public final class SurvivalTranslationManager extends TranslationManager {
	public SurvivalTranslationManager(Logger logger) {
		super(logger);
	}

	@Override
	protected Key name() {
		return Key.key("fabicraft", "survival");
	}
}
