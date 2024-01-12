package me.goodbee.animalfacts.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class catfact implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        try {
            URL url = new URL("https://meowfacts.herokuapp.com");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.connect();

            int status = con.getResponseCode();
            System.out.println(status);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            JsonElement jsonElement = JsonParser.parseString(content.toString());

            String fact = jsonElement.getAsJsonObject().get("data").toString().replace('[', ' ').replace('"', ' ').replace(']', ' ');

            sender.sendMessage(ChatColor.GREEN + fact);
        } catch (IOException e) {
            sender.sendMessage("§cAn error occured! :(");
            Bukkit.getServer().getLogger().warning(e.toString());
            return true;
        }
        return false;
    }
}
