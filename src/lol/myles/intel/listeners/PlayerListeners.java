package lol.myles.intel.listeners;

import com.google.common.collect.Lists;
import lol.myles.intel.Intel;
import lol.myles.intel.managers.ConfigManager;
import lol.myles.intel.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.List;

/**
 * Created by VBS Aka TheS7W.
 * Package name: lol.myles.intel.listeners
 * Date: 14/05/2016
 * Project name: Intel
 */
public class PlayerListeners implements Listener {

    Intel main = Intel.getInstance();
    ConfigManager cm = main.getConfigManager();

    public PlayerListeners() {
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void handleLaunchEvent(ProjectileLaunchEvent event) {
        if(event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            if(event.getEntity() instanceof EnderPearl) {
                Projectile projectile = event.getEntity();
                if (projectile.getType() == EntityType.ENDER_PEARL) {
                    player.spigot().setCollidesWithEntities(false);
                    projectile.setPassenger(player);
                }
            }
        }
    }



    @EventHandler
    public void handleTeleportEvent(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if(event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void handleDismountEvent(EntityDismountEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(player != null && player.getVehicle() instanceof EnderPearl) {
                player.getVehicle().remove();
                player.eject();
            }
        }
    }


    @EventHandler
    public void handleMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(event.getTo().getBlockY() <= cm.getBelowY()) {
            if(cm.getSpawnLocation() == null) {
                player.sendMessage(ChatColor.RED + "Spawn has not been set yet! Use /setspawn at a location you want");
                return;
            } else {
                player.teleport(cm.getSpawnLocation());
                if (player.getVehicle() != null) {
                    player.eject();
                }
            }
        }
    }

    @EventHandler
    public void handleHitEvent(ProjectileHitEvent event) {
        if(event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            if(event.getEntity() instanceof EnderPearl && player.getVehicle() instanceof EnderPearl) {
                player.eject();
                player.teleport(player.getLocation().add(0.0D, 0.3D, 0.0D));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePickupItemEvent(PlayerPickupItemEvent event) {
        event.setCancelled(false);
    }

    @EventHandler
    public void handleQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void handleJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        final Player player = event.getPlayer();
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, new ItemStack(Material.ENDER_PEARL));
        player.teleport(cm.getSpawnLocation());

        if(player.hasPermission("intel.owner")) {
            ItemStack leatherHelmet = new ItemBuilder(Material.LEATHER_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.RED).build();
            ItemStack leatherChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.RED).build();
            ItemStack leatherLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.RED).build();
            ItemStack leatherBoots = new ItemBuilder(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.RED).build();

            player.getInventory().setHelmet(leatherHelmet);
            player.getInventory().setChestplate(leatherChestplate);
            player.getInventory().setLeggings(leatherLeggings);
            player.getInventory().setBoots(leatherBoots);
            return;
        }
        else if(player.hasPermission("intel.developer")) {
            ItemStack leatherHelmet = new ItemBuilder(Material.LEATHER_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.TEAL).build();
            ItemStack leatherChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.TEAL).build();
            ItemStack leatherLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.TEAL).build();
            ItemStack leatherBoots = new ItemBuilder(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.TEAL).build();

            player.getInventory().setHelmet(leatherHelmet);
            player.getInventory().setChestplate(leatherChestplate);
            player.getInventory().setLeggings(leatherLeggings);
            player.getInventory().setBoots(leatherBoots);
            return;
        }
        else if(player.hasPermission("intel.admin")) {
            ItemStack leatherHelmet = new ItemBuilder(Material.LEATHER_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.MAROON).build();
            ItemStack leatherChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.MAROON).build();
            ItemStack leatherLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.MAROON).build();
            ItemStack leatherBoots = new ItemBuilder(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.MAROON).build();

            player.getInventory().setHelmet(leatherHelmet);
            player.getInventory().setChestplate(leatherChestplate);
            player.getInventory().setLeggings(leatherLeggings);
            player.getInventory().setBoots(leatherBoots);
            return;
        }
        else if(player.hasPermission("intel.mod")) {
            ItemStack leatherHelmet = new ItemBuilder(Material.LEATHER_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.PURPLE).build();
            ItemStack leatherChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.PURPLE).build();
            ItemStack leatherLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.PURPLE).build();
            ItemStack leatherBoots = new ItemBuilder(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.PURPLE).build();

            player.getInventory().setHelmet(leatherHelmet);
            player.getInventory().setChestplate(leatherChestplate);
            player.getInventory().setLeggings(leatherLeggings);
            player.getInventory().setBoots(leatherBoots);
            return;
        }
        else if(player.hasPermission("intel.elite")) {
            ItemStack leatherHelmet = new ItemBuilder(Material.LEATHER_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.FUCHSIA).build();
            ItemStack leatherChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.FUCHSIA).build();
            ItemStack leatherLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.FUCHSIA).build();
            ItemStack leatherBoots = new ItemBuilder(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.FUCHSIA).build();

            player.getInventory().setHelmet(leatherHelmet);
            player.getInventory().setChestplate(leatherChestplate);
            player.getInventory().setLeggings(leatherLeggings);
            player.getInventory().setBoots(leatherBoots);
            return;
        }
        else if(player.hasPermission("intel.premium")) {
            ItemStack leatherHelmet = new ItemBuilder(Material.LEATHER_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.AQUA).build();
            ItemStack leatherChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.AQUA).build();
            ItemStack leatherLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.AQUA).build();
            ItemStack leatherBoots = new ItemBuilder(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.AQUA).build();

            player.getInventory().setHelmet(leatherHelmet);
            player.getInventory().setChestplate(leatherChestplate);
            player.getInventory().setLeggings(leatherLeggings);
            player.getInventory().setBoots(leatherBoots);
            return;
        }
        else if(player.hasPermission("intel.basic")) {
            ItemStack leatherHelmet = new ItemBuilder(Material.LEATHER_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.fromBGR(154, 255, 50)).build();
            ItemStack leatherChestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.fromBGR(154, 255, 50)).build();
            ItemStack leatherLeggings = new ItemBuilder(Material.LEATHER_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.fromBGR(154, 255, 50)).build();
            ItemStack leatherBoots = new ItemBuilder(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.fromBGR(154, 255, 50)).build();

            player.getInventory().setHelmet(leatherHelmet);
            player.getInventory().setChestplate(leatherChestplate);
            player.getInventory().setLeggings(leatherLeggings);
            player.getInventory().setBoots(leatherBoots);
            return;
        } else {
            ItemStack leatherBoots = new ItemBuilder(Material.LEATHER_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).color(Color.WHITE).build();
            player.getInventory().setBoots(leatherBoots);
            return;
        }
    }

    @EventHandler
    public void handleDamageEvent(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void handleDropItemEvent(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleWeatherChangeEvent(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleSpawnEvent(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.getItemInHand().getType() == Material.ENDER_PEARL) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR) {
                player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
            } else if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);
                player.updateInventory();
            }
        }
    }

    @EventHandler
    public void handleEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if ((event.getDamager() instanceof Player)) {
            Player damager = (Player) event.getDamager();
            if ((event.getEntity() instanceof Player)) {
                Player damagee = (Player) event.getEntity();
                if (!damagee.hasPermission("intel.owner") || !damagee.hasPermission("intel.developer") || !damagee.hasPermission("intel.admin") || !damagee.hasPermission("intel.moderator")) {
                    damager.hidePlayer(damagee);
                    damager.sendMessage(ChatColor.GREEN + "Poof...");
                }
            }
        }
    }

}
