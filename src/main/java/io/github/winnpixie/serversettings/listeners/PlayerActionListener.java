package io.github.winnpixie.serversettings.listeners;

import io.github.winnpixie.commons.spigot.listeners.EventListener;
import io.github.winnpixie.serversettings.Config;
import io.github.winnpixie.serversettings.ServerSettings;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerActionListener extends EventListener<ServerSettings> {
    public PlayerActionListener(ServerSettings plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onPlayerDamaged(EntityDamageByEntityEvent event) {
        if (!Config.OVERRIDE_PVP) return;
        if (Config.ALLOW_PVP) return;
        if (!(event.getEntity() instanceof Player)) return;

        Entity killer = event.getDamager();
        if (killer instanceof Projectile projectile) {
            if (projectile.getShooter() instanceof LivingEntity living) killer = living;
        }

        if (killer instanceof Player) event.setCancelled(true);
    }
}
