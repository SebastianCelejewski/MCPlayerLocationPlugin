package pl.sebcel.minecraft.playerlocation;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SavePlayersLocationTask implements Runnable {

    private final DateFormat fileDateFormat = new SimpleDateFormat("yyyy-MM");
    private final DateFormat logEntryDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final JavaPlugin plugin;

    public SavePlayersLocationTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Date now = new Date();

        Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
        for (Player player : players) {
            Location playerLocation = player.getLocation();
            String playerName = player.getDisplayName();
            int x = playerLocation.getBlockX();
            int y = playerLocation.getBlockY();
            int z = playerLocation.getBlockZ();
            int dimension = playerLocation.getWorld().getEnvironment().ordinal();
            savePositionToFile(playerName, now, dimension, x, y, z);
        }
    }

    private void savePositionToFile(String playerName, Date date, int dimension, int x, int y, int z) {
        try {
            String fileName = "location-" + playerName + "-" + fileDateFormat.format(date) + ".csv";
            FileWriter fw = new FileWriter(fileName, true);
            String line = "\"" + logEntryDateFormat.format(date) + "\"," + dimension + "," + x + "," + y + "," + z + "\r\n";
            fw.write(line);
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}