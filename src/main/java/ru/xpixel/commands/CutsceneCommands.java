package ru.xpixel.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.mayaqq.cutscenary.api.CutscenaryRegistries;
import dev.mayaqq.cutscenary.api.Cutscene;
import dev.mayaqq.cutscenary.commands.CutsceneSuggestionProvider;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CutsceneCommands {

    public CutsceneCommands() {
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("cs").requires(cs -> cs.hasPermissionLevel(4))
            .then(CommandManager.argument("cutsceneId", IdentifierArgumentType.identifier()).suggests(new CutsceneSuggestionProvider())
                .then(argument("target", EntityArgumentType.player())
                    .executes(CutsceneCommands::cutsceneCommandWithPlayer)
                )
            )
        );
    }

    public static int cutsceneCommandWithPlayer(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity target = EntityArgumentType.getPlayer(context, "target");
        Cutscene cutscene = CutscenaryRegistries.CUTSCENE.get(IdentifierArgumentType.getIdentifier(context, "cutsceneId"));
        cutscene.play(cutscene.id(), target);
        return 0;
    }
}
