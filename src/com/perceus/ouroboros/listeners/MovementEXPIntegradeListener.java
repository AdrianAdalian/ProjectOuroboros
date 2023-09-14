package com.perceus.ouroboros.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.DisplaySlot;

import com.perceus.ouroboros.Ouroboros;
import com.perceus.ouroboros.playerdata.PlayerDataFileHolder;

public class MovementEXPIntegradeListener implements Listener
{
	@EventHandler
	public void onWalk(PlayerMoveEvent e) 
	{
		UUID uuid = e.getPlayer().getUniqueId();
		int stat = e.getPlayer().getStatistic(Statistic.WALK_ONE_CM);
		if (stat > 100000) 
		{
			PlayerDataFileHolder.getPlayerData(uuid).setMovementEXP(PlayerDataFileHolder.getPlayerData(uuid).getMovementEXP() + 1);
			if (PlayerDataFileHolder.getPlayerData(uuid).getMovementEXP() >= 100)
			{
				PlayerDataFileHolder.getPlayerData(uuid).setMovementEXP(0);
				PlayerDataFileHolder.getPlayerData(uuid).setMovementLevel(PlayerDataFileHolder.getPlayerData(uuid).getMovementLevel() + 1);
				PlayerDataFileHolder.getPlayerData(uuid).setSkillPoints(PlayerDataFileHolder.getPlayerData(uuid).getSkillPoints() + 1);
				SkillBoard.setScoreboard(e.getPlayer());
				Bukkit.getScheduler().runTaskLater(Ouroboros.instance, ()->e.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR), 60);
			}
			e.getPlayer().setStatistic(Statistic.WALK_ONE_CM, 0);
		}
		
		
		
	}
}
