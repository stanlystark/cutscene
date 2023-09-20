package ru.xpixel.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import ru.xpixel.CutsceneMod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CutsceneConfig {
    public static final CutsceneConfig INSTANCE = new CutsceneConfig();
    public final Path configFile = FabricLoader.getInstance().getConfigDir().resolve("cutscenes.json");
    private final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
    public Map<String, ArrayList<String>> cutscenes = new HashMap<>();


    public CutsceneConfig() {
    }


    public void create() {
        try {
            Files.deleteIfExists(this.configFile);
            ArrayList<String> preview = new ArrayList<String>(List.of(new String[]{"Hello world!", "Edit this in config/cutscenes.json!"}));
            this.cutscenes.put("preview", preview);
            Files.writeString(this.configFile, this.gson.toJson(this.cutscenes));
        } catch (Exception var2) {
            CutsceneMod.LOGGER.error("Failed to create config file!");
        }

    }

    public void load() {
        try {
            if (Files.notExists(this.configFile)) {
                this.create();
                return;
            }

            this.cutscenes = this.gson.fromJson(Files.readString(this.configFile), Map.class);
        } catch (Exception var2) {
            CutsceneMod.LOGGER.error("Failed to load config file!");
        }

    }
}
