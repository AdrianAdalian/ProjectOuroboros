package com.perceus.ouroboros;

import java.io.FileNotFoundException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.perceus.ouroboros.playerdata.PlayerDataFileHolder;
import com.perceus.ouroboros.utilities.FileUtilities;
import com.perceus.ouroboros.utilities.PrintUtils;

import net.md_5.bungee.api.ChatColor;

public class SkillBoard implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws FileNotFoundException 
	{
		
		
		PlayerDataFileHolder.getPlayerData(e.getPlayer().getUniqueId());
		setScoreboard(e.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) throws FileNotFoundException 
	{
		FileUtilities.toFile(PlayerDataFileHolder.getPath(e.getPlayer().getUniqueId()).toString(), PlayerDataFileHolder.getPlayerData(e.getPlayer().getUniqueId()));
	}
	
	public void setScoreboard(Player p) throws FileNotFoundException 
	{
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("Player Stats", Criteria.DUMMY, p.getName() + PrintUtils.ColorParser("&r&f's Stats:"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.getScore(ChatColor.WHITE + "General XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getGeneralEXP());
		obj.getScore(ChatColor.WHITE + "Kill XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getKillEXP());
		obj.getScore(ChatColor.WHITE + "Movement XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getMovementEXP());
		p.setScoreboard(board);
	}
	
	public static void updateScoreboard(Player p) throws FileNotFoundException
	{
		Scoreboard board = p.getScoreboard();
		Objective obj = board.getObjective(DisplaySlot.SIDEBAR);
		obj.getScore(ChatColor.WHITE + "General XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getGeneralEXP());
		obj.getScore(ChatColor.WHITE + "Kill XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getKillEXP());
		obj.getScore(ChatColor.WHITE + "Movement XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getMovementEXP());
		p.setScoreboard(board);
	}
}
