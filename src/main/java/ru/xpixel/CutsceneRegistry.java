package ru.xpixel;

import dev.mayaqq.cutscenary.api.CutscenaryRegistries;
import dev.mayaqq.cutscenary.api.Cutscene;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.xpixel.config.CutsceneConfig;

import java.util.ArrayList;
import java.util.Map;

public class CutsceneRegistry {

    public static void register() {
        for(Map.Entry<String, ArrayList<String>> entry : CutsceneConfig.INSTANCE.cutscenes.entrySet()) {
            String path = entry.getKey();
            ArrayList<String> values = entry.getValue();

            Registry.register(CutscenaryRegistries.CUTSCENE, new Identifier("play", path),
                new Cutscene(
                    new Identifier("play", path),
                    values.stream().map(Text::of).toArray(Text[]::new)
                )
            );
        }
    }
}
