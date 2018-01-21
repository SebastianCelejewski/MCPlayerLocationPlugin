package pl.sebcel.minecraft.playerlocation;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerLocationPlugin extends JavaPlugin implements Listener {
    
    @Override
    public void onEnable() {
        getLogger().info("PlayerLocationPlugin has been enabled.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("PlayerLocationPlugin has been disabled.");
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        getLogger().info("Player " + event.getPlayer().getDisplayName() + " logged in.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        getLogger().info("Player " + event.getPlayer().getDisplayName() + " logged out.");
    }
}