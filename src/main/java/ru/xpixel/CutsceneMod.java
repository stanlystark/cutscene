package ru.xpixel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.xpixel.commands.CutsceneCommands;
import ru.xpixel.config.CutsceneConfig;

public class CutsceneMod implements ModInitializer {
	public static CutsceneMod INSTANCE;
    public static final Logger LOGGER = LoggerFactory.getLogger("cutscene");

	@Override
	public void onInitialize() {
		LOGGER.info("Loading...");
		INSTANCE = this;
		CutsceneConfig.INSTANCE.load();
		CutsceneRegistry.register();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			CutsceneCommands.register(dispatcher);
		});
	}
}