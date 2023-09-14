package com.perceus.ouroboros.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import com.perceus.ouroboros.Ouroboros;
import com.perceus.ouroboros.playerdata.PlayerDataFileHolder;

public class GeneralEXPIntegradeListener implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e) 
	{
		UUID uuid = e.getPlayer().getUniqueId();
		
		new BukkitRunnable()
		{
			@Override
			public void run() 
			{
				if (!e.getPlayer().isOnline()) 
				{
					this.cancel();
				}
				PlayerDataFileHolder.getPlayerData(uuid).setGeneralEXP(PlayerDataFileHolder.getPlayerData(uuid).getGeneralEXP() + 1);
				
				if (PlayerDataFileHolder.getPlayerData(uuid).getGeneralEXP() >= 1000) 
				{
					PlayerDataFileHolder.getPlayerData(uuid).setGeneralEXP(0);
					PlayerDataFileHolder.getPlayerData(uuid).setGeneralLevel(PlayerDataFileHolder.getPlayerData(uuid).getGeneralLevel() + 1);
					PlayerDataFileHolder.getPlayerData(uuid).setSkillPoints(PlayerDataFileHolder.getPlayerData(uuid).getSkillPoints() +1);
				}
				SkillBoard.setScoreboard(e.getPlayer());
				Bukkit.getScheduler().runTaskLater(Ouroboros.instance, ()->e.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR), 60);
			}
		}.runTaskTimer(Ouroboros.instance, 1200, 1200);
	}
}
