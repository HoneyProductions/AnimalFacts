package me.goodbee.animalfacts;

import me.goodbee.animalfacts.commands.catfact;
import me.goodbee.animalfacts.commands.dogfact;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnimalFacts extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("catfact").setExecutor(new catfact());
        getCommand("dogfact").setExecutor(new dogfact());
    }

    @Override
    public void onDisable() {

    }
}
