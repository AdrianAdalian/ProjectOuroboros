package com.perceus.ouroboros.listeners;

import java.io.FileNotFoundException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;

import com.perceus.ouroboros.playerdata.PlayerDataFileHolder;
import com.perceus.ouroboros.utilities.FileUtilities;

public class OnJoinOnQuitListener implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws FileNotFoundException 
	{
		e.getPlayer().sendMessage("Project Ouroboros, ONLINE. Running v:<BETA RELEASE>");
		PlayerDataFileHolder.getPlayerData(e.getPlayer().getUniqueId());
		SkillBoard.setScoreboard(e.getPlayer());
		e.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) throws FileNotFoundException 
	{
		FileUtilities.toFile(PlayerDataFileHolder.getPath(e.getPlayer().getUniqueId()).toString(), PlayerDataFileHolder.getPlayerData(e.getPlayer().getUniqueId()));
	}
}
