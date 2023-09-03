package com.perceus.ouroboros;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import com.perceus.ouroboros.listeners.SkillBoard;
import com.perceus.ouroboros.playerdata.PlayerDataFileHolder;

public class OuroborosCommand implements CommandExecutor, TabCompleter
{
	public OuroborosCommand() 
	{
		Bukkit.getPluginCommand("skills").setTabCompleter(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args)
	{
		Player player = (Player) sender;
		
		if (args.length == 0) 
		{
			player.sendMessage("Argument(s) not found, please try again.");
			return false;
		}
		
		if (args[0].equals("scoreboard")) 
		{			
			try
			{
				SkillBoard.setScoreboard(player);
			} catch (FileNotFoundException e){e.printStackTrace();}
			Bukkit.getScheduler().runTaskLater(Ouroboros.instance, ()->{
				player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			}, 100);
			return true;
		}
		
		if (args[0].equals("menu")) 
		{
			player.sendMessage("Unimplemented Argument");
			return false;
		}
		
		if (args[0].equals("reset")) 
		{
			if (!player.isOp()) 
			{
				player.sendMessage("You don't have permissions to this command.");
				return false;
			}
			UUID uuid = player.getPlayer().getUniqueId();
			try
			{
				PlayerDataFileHolder.getPlayerData(uuid).setGeneralEXP(0);
				PlayerDataFileHolder.getPlayerData(uuid).setGeneralLevel(0);
				PlayerDataFileHolder.getPlayerData(uuid).setKillEXP(0);
				PlayerDataFileHolder.getPlayerData(uuid).setKillLevel(0);
				PlayerDataFileHolder.getPlayerData(uuid).setMovementEXP(0);
				PlayerDataFileHolder.getPlayerData(uuid).setMovementLevel(0);
				PlayerDataFileHolder.getPlayerData(uuid).setSkillPoints(0);
				
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args)
	{
		
		return switch (args.length) 
		{
			case 0 -> List.of("scoreboard", "menu", "reset");
			case 1 -> List.of("scoreboard", "menu", "reset");
			case 2 -> {
				yield switch(args[0]) 
				{
					case "scoreboard" -> List.of();
					case "menu" -> List.of();
					case "reset" -> List.of();
				default -> List.of();
				};
			}
			case 3 -> List.of();
		default -> List.of();
		};
	}
	
}
