package ru.xpixel;

import dev.mayaqq.cutscenary.api.CutscenaryRegistries;
import dev.mayaqq.cutscenary.api.Cutscene;
import net.minecraft.registry.Registry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.xpixel.config.CutsceneConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CutsceneRegistry {

    public static void register() {

        for(Map.Entry<String, String[]> entry : CutsceneConfig.INSTANCE.cutscenes.entrySet()) {
            String path = entry.getKey();
            String[] values = entry.getValue();

            Registry.register(CutscenaryRegistries.CUTSCENE, new Identifier("play", path),
                new Cutscene(
                        new Identifier("play", path),
                        Arrays.stream(values).map(Text::of).toArray(Text[]::new)
                )
            );
        }
    }
}
