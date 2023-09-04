package com.perceus.ouroboros.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.perceus.ouroboros.playerdata.PlayerDataFileHolder;
import com.perceus.ouroboros.utilities.PrintUtils;

import net.md_5.bungee.api.ChatColor;

public class SkillBoard implements Listener
{
	
	public static void setScoreboard(Player p)
	{
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("Player Stats", Criteria.DUMMY, p.getName() + PrintUtils.ColorParser("&r&f's Stats:"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.getScore(ChatColor.WHITE + "Skill Points:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getSkillPoints());
		
		obj.getScore(ChatColor.WHITE + "General XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getGeneralEXP());
		obj.getScore(ChatColor.WHITE + "General Level:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getGeneralLevel());
		
		obj.getScore(ChatColor.WHITE + "Kill XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getKillEXP());
		obj.getScore(ChatColor.WHITE + "Kill Level:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getKillLevel());
		
		obj.getScore(ChatColor.WHITE + "Movement XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getMovementEXP());
		obj.getScore(ChatColor.WHITE + "Movement Level:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getMovementLevel());
		p.setScoreboard(board);
	}
	
	public static void updateScoreboard(Player p)
	{
		Scoreboard board = p.getScoreboard();
		Objective obj = board.getObjective(DisplaySlot.SIDEBAR);
		obj.getScore(ChatColor.WHITE + "Skill Points:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getSkillPoints());
		
		obj.getScore(ChatColor.WHITE + "General XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getGeneralEXP());
		obj.getScore(ChatColor.WHITE + "General Level:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getGeneralLevel());
		
		obj.getScore(ChatColor.WHITE + "Kill XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getKillEXP());
		obj.getScore(ChatColor.WHITE + "Kill Level:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getKillLevel());
		
		obj.getScore(ChatColor.WHITE + "Movement XP:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getMovementEXP());
		obj.getScore(ChatColor.WHITE + "Movement Level:" + ChatColor.WHITE).setScore(PlayerDataFileHolder.getPlayerData(p.getUniqueId()).getMovementLevel());
		p.setScoreboard(board);
	}
}
