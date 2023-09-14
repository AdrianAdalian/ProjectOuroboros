package com.perceus.ouroboros.listeners;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scoreboard.DisplaySlot;

import com.perceus.ouroboros.Ouroboros;
import com.perceus.ouroboros.playerdata.PlayerDataFileHolder;

public class KillEXPIntegradeListener implements Listener
{
	public static Map<EntityType, Integer> mapOfExp = new EnumMap<>(EntityType.class);
	
	static 
	{
		mapOfExp.put(EntityType.ALLAY, 500);
		mapOfExp.put(EntityType.AXOLOTL, 10);
		mapOfExp.put(EntityType.BAT, 5);
		mapOfExp.put(EntityType.BEE, 5);
		mapOfExp.put(EntityType.BLAZE, 60);
		mapOfExp.put(EntityType.CAMEL, 1);
		mapOfExp.put(EntityType.CAT, 1);
		mapOfExp.put(EntityType.CAVE_SPIDER, 20);
		mapOfExp.put(EntityType.CHICKEN, 1);
		mapOfExp.put(EntityType.COD, 2);
		mapOfExp.put(EntityType.COW, 1);
		mapOfExp.put(EntityType.CREEPER, 15);
		mapOfExp.put(EntityType.DOLPHIN, 1);
		mapOfExp.put(EntityType.DONKEY, 1);
		mapOfExp.put(EntityType.DROWNED, 30);
		mapOfExp.put(EntityType.ELDER_GUARDIAN, 1000);
		mapOfExp.put(EntityType.ENDER_DRAGON, 1500);
		mapOfExp.put(EntityType.ENDERMAN, 50);
		mapOfExp.put(EntityType.ENDERMITE, 1);
		mapOfExp.put(EntityType.EVOKER, 45);
		mapOfExp.put(EntityType.FOX, 1);
		mapOfExp.put(EntityType.FROG, 1);
		mapOfExp.put(EntityType.GHAST, 35);
		mapOfExp.put(EntityType.GIANT, 150);
		mapOfExp.put(EntityType.GLOW_SQUID, 1);
		mapOfExp.put(EntityType.GOAT, 1);
		mapOfExp.put(EntityType.GUARDIAN, 75);
		mapOfExp.put(EntityType.HOGLIN, 35);
		mapOfExp.put(EntityType.HORSE, 1);
		mapOfExp.put(EntityType.HUSK, 20);
		mapOfExp.put(EntityType.ILLUSIONER, 50);
		mapOfExp.put(EntityType.IRON_GOLEM, 25);
		mapOfExp.put(EntityType.LLAMA, 1);
		mapOfExp.put(EntityType.MAGMA_CUBE, 10);
		mapOfExp.put(EntityType.MULE, 1);
		mapOfExp.put(EntityType.MUSHROOM_COW, 2);
		mapOfExp.put(EntityType.OCELOT, 2);
		mapOfExp.put(EntityType.PANDA, 1);
		mapOfExp.put(EntityType.PARROT, 1);
		mapOfExp.put(EntityType.PHANTOM, 55);
		mapOfExp.put(EntityType.PIG, 1);
		mapOfExp.put(EntityType.PIGLIN, 15);
		mapOfExp.put(EntityType.PIGLIN_BRUTE, 40);
		mapOfExp.put(EntityType.PILLAGER, 25);
		mapOfExp.put(EntityType.PLAYER, 200);
		mapOfExp.put(EntityType.POLAR_BEAR, 15);
		mapOfExp.put(EntityType.PUFFERFISH, 10);
		mapOfExp.put(EntityType.RABBIT, 1);
		mapOfExp.put(EntityType.RAVAGER, 150);
		mapOfExp.put(EntityType.SALMON, 1);
		mapOfExp.put(EntityType.SHEEP, 1);
		mapOfExp.put(EntityType.SHULKER, 70);
		mapOfExp.put(EntityType.SILVERFISH, 5);
		mapOfExp.put(EntityType.SKELETON, 20);
		mapOfExp.put(EntityType.SKELETON_HORSE, 10);
		mapOfExp.put(EntityType.SLIME, 1);
		mapOfExp.put(EntityType.SNIFFER, 1);
		mapOfExp.put(EntityType.SNOWMAN, 5);
		mapOfExp.put(EntityType.SPIDER, 10);
		mapOfExp.put(EntityType.SQUID, 1);
		mapOfExp.put(EntityType.STRAY, 25);
		mapOfExp.put(EntityType.STRIDER, 20);
		mapOfExp.put(EntityType.TADPOLE, 1);
		mapOfExp.put(EntityType.TROPICAL_FISH, 2);
		mapOfExp.put(EntityType.TURTLE, 5);
		mapOfExp.put(EntityType.VEX, 35);
		mapOfExp.put(EntityType.VILLAGER, 1);
		mapOfExp.put(EntityType.VINDICATOR, 50);
		mapOfExp.put(EntityType.WANDERING_TRADER, 1);
		mapOfExp.put(EntityType.WARDEN, 2500);
		mapOfExp.put(EntityType.WITCH, 25);
		mapOfExp.put(EntityType.WITHER, 1500);
		mapOfExp.put(EntityType.WITHER_SKELETON, 70);
		mapOfExp.put(EntityType.WOLF, 1);
		mapOfExp.put(EntityType.ZOGLIN, 40);
		mapOfExp.put(EntityType.ZOMBIE, 15);
		mapOfExp.put(EntityType.ZOMBIE_HORSE, 1);
		mapOfExp.put(EntityType.ZOMBIE_VILLAGER, 10);
		mapOfExp.put(EntityType.ZOMBIFIED_PIGLIN, 30);
		
	}
	
	@EventHandler
	public void onKill(EntityDamageByEntityEvent e) 
	{
		if (!(e.getDamager() instanceof Player)) 
		{
			return;
		}
		
		Player p = (Player) e.getDamager();
		UUID uuid = p.getPlayer().getUniqueId();
		if (e.getEntity() instanceof LivingEntity le) 
		{
			if (le.getHealth() - e.getDamage() <= 0) 
			{
				PlayerDataFileHolder.getPlayerData(uuid).setKillEXP(PlayerDataFileHolder.getPlayerData(uuid).getKillEXP() + mapOfExp.get(le.getType()));
				if (PlayerDataFileHolder.getPlayerData(uuid).getKillEXP() >= 10000) 
				{
					PlayerDataFileHolder.getPlayerData(uuid).setKillEXP(0);
					PlayerDataFileHolder.getPlayerData(uuid).setKillLevel(PlayerDataFileHolder.getPlayerData(uuid).getKillLevel() + 1);
					PlayerDataFileHolder.getPlayerData(uuid).setSkillPoints(PlayerDataFileHolder.getPlayerData(uuid).getSkillPoints() + 1);
				}
				SkillBoard.setScoreboard(p.getPlayer());
				Bukkit.getScheduler().runTaskLater(Ouroboros.instance, ()->p.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR), 60);
			}
		}
		
		
	}
}
