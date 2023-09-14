package com.perceus.ouroboros;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceus.ouroboros.listeners.GeneralEXPIntegradeListener;
import com.perceus.ouroboros.listeners.KillEXPIntegradeListener;
import com.perceus.ouroboros.listeners.MovementEXPIntegradeListener;
import com.perceus.ouroboros.listeners.OnJoinOnQuitListener;
import com.perceus.ouroboros.listeners.SkillBoard;
import com.perceus.ouroboros.utilities.PrintUtils;

public class Ouroboros extends JavaPlugin
{
	public static Ouroboros instance;
	
	@Override
	public void onEnable() 
	{
		instance = this;
		instance.getCommand("skills").setExecutor(new OuroborosCommand());
		
		PrintUtils.Print("&aOuroboros, ONLINE.");
		Bukkit.getPluginManager().registerEvents(new SkillBoard(), this);
		Bukkit.getPluginManager().registerEvents(new OnJoinOnQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new GeneralEXPIntegradeListener(), this);
		Bukkit.getPluginManager().registerEvents(new MovementEXPIntegradeListener(), this);
		Bukkit.getPluginManager().registerEvents(new KillEXPIntegradeListener(), this);
	}
	
	@Override
	public void onDisable() 
	{
		
	}
}
