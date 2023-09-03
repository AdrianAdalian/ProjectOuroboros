package com.perceus.ouroboros.listeners;

import java.io.FileNotFoundException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.perceus.ouroboros.Ouroboros;
import com.perceus.ouroboros.playerdata.PlayerDataFileHolder;

public class GeneralEXPIntegradeListener implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e) 
	{
		UUID uuid = e.getPlayer().getUniqueId();
		Bukkit.getScheduler().runTaskTimer(Ouroboros.instance, ()->{
			if (!e.getPlayer().isOnline()) 
			{
				return;
			}
			try
			{
				PlayerDataFileHolder.getPlayerData(uuid).setGeneralEXP(PlayerDataFileHolder.getPlayerData(uuid).getGeneralEXP() + 1);
			} catch (FileNotFoundException e1){
				e1.printStackTrace();
				return;}
			
			try
			{
				if (PlayerDataFileHolder.getPlayerData(uuid).getGeneralEXP() == 1000) 
				{
					PlayerDataFileHolder.getPlayerData(uuid).setGeneralEXP(0);
					PlayerDataFileHolder.getPlayerData(uuid).setGeneralLevel(PlayerDataFileHolder.getPlayerData(uuid).getGeneralLevel() + 1);
					PlayerDataFileHolder.getPlayerData(uuid).setSkillPoints(PlayerDataFileHolder.getPlayerData(uuid).getSkillPoints() +1);
				}
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
				return;
			}
		}, 1200, 1200);
	}
}
