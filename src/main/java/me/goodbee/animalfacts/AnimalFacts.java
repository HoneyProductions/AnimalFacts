package me.goodbee.animalfacts;

import me.goodbee.animalfacts.commands.catfact;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnimalFacts extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("catfact").setExecutor(new catfact());
    }

    @Override
    public void onDisable() {

    }
}
