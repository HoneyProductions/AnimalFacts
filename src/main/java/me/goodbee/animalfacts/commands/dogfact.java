package me.goodbee.animalfacts.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class dogfact implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder(URI.create("https://dogapi.dog/api/v2/facts")).header("accept", "application/json").build();

            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String fact = JsonParser.parseString(response.body().toString()).getAsJsonObject().get("data").getAsJsonArray().get(0).getAsJsonObject().get("attributes").getAsJsonObject().get("body").getAsString();

            sender.sendMessage(ChatColor.GREEN + fact);

        } catch (RuntimeException | IOException | InterruptedException e) {
            sender.sendMessage("§cAn error occured! :(");
            Bukkit.getServer().getLogger().warning(e.toString());
            return true;
        }
        return false;
    }
}
