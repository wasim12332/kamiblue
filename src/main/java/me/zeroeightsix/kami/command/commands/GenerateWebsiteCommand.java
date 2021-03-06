package me.zeroeightsix.kami.command.commands;

import me.zeroeightsix.kami.KamiMod;
import me.zeroeightsix.kami.command.Command;
import me.zeroeightsix.kami.module.Module;
import me.zeroeightsix.kami.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author S-B99
 * Updated by S-B99 on 18/03/20
 */
public class GenerateWebsiteCommand extends Command {
    public GenerateWebsiteCommand() {
        super("genwebsite", null);
        setDescription("Generates the module page for the website");
    }

    private static String nameAndDescription(Module module) {
        return "<li>" + module.getName() + "<p><i>" + module.getDescription() + "</i></p></li>";
    }

    @Override
    public void call(String[] args) {
        List<Module> mods = new ArrayList<>(ModuleManager.getModules());
        String[] modCategories = new String[]{"Chat", "Combat", "Gui", "Misc", "Movement", "Player", "Render"};
        List<String> modCategoriesList = new ArrayList<>(java.util.Arrays.asList(modCategories));

        List<String> modsChat = new ArrayList<>();
        List<String> modsCombat = new ArrayList<>();
        List<String> modsGui = new ArrayList<>();
        List<String> modsMisc = new ArrayList<>();
        List<String> modsMovement = new ArrayList<>();
        List<String> modsPlayer = new ArrayList<>();
        List<String> modsRender = new ArrayList<>();

        mods.forEach(module -> {
            switch (module.getCategory()) {
                case CHAT:
                    modsChat.add(nameAndDescription(module));
                case COMBAT:
                    modsCombat.add(nameAndDescription(module));
                case GUI:
                    modsGui.add(nameAndDescription(module));
                case MISC:
                    modsMisc.add(nameAndDescription(module));
                case MOVEMENT:
                    modsMovement.add(nameAndDescription(module));
                case PLAYER:
                    modsPlayer.add(nameAndDescription(module));
                case RENDER:
                    modsRender.add(nameAndDescription(module));
            }
        });

        modCategoriesList.forEach(modCategory -> {
            KamiMod.log.info("<details>");
            KamiMod.log.info("    <summary>" + modCategory + "</summary>");
            KamiMod.log.info("    <p><ul>");
            mods.forEach(module -> {
                if (module.getCategory().toString().equalsIgnoreCase(modCategory)) {
                    KamiMod.log.info("        <li>" + module.getName() + "<p><i>" + module.getDescription() + "</i></p></li>");
                }
            });
            KamiMod.log.info("    </ul></p>"); // cat#$*UWUnuzzl3s70U
            KamiMod.log.info("</details>");

        });

        Command.sendChatMessage(getLabel().substring(0, 1).toUpperCase() + getLabel().substring(1) + ": Generated website to log file!");
    }
}
