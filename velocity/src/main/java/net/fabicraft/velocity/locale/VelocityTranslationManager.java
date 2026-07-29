package net.fabicraft.velocity.locale;

import net.fabicraft.common.locale.TranslationManager;
import net.kyori.adventure.key.Key;
import org.slf4j.Logger;

public final class VelocityTranslationManager extends TranslationManager {
	public VelocityTranslationManager(Logger logger) {
		super(logger);
	}

	@Override
	protected Key name() {
		return Key.key("fabicraft", "velocity");
	}
}
