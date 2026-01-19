package net.fabicraft.survival.spawn;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.fabicraft.survival.FabiCraftSurvival;
import org.bukkit.Location;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class FirstSpawnManager {
	private final Gson gson = new Gson();
	private final Path path;
	private final FabiCraftSurvival plugin;
	private Location location;

	public FirstSpawnManager(FabiCraftSurvival plugin) {
		this.plugin = plugin;
		this.path = plugin.getDataPath().resolve("firstspawn.json");
	}

	public void load() {
		if (!Files.exists(this.path)) {
			return;
		}
		try (BufferedReader reader = Files.newBufferedReader(this.path)) {
			Type type = new TypeToken<Map<String, Object>>() {
			}.getType();
			Map<String, Object> serialized = this.gson.fromJson(reader, type);
			this.location = Location.deserialize(serialized);
		} catch (IOException e) {
			this.plugin.getSLF4JLogger().error("Failed to read firstspawn.json", e);
		}
	}

	public void location(Location location) {
		this.location = location;
	}

	public Location location() {
		return this.location;
	}

	public void save() throws IOException {
		if (this.location == null) {
			throw new IllegalStateException("Can't save null spawn");
		}
		Files.createDirectories(this.path.getParent());
		try (BufferedWriter writer = Files.newBufferedWriter(this.path)) {
			this.gson.toJson(this.location.serialize(), writer);
		}
	}
}
