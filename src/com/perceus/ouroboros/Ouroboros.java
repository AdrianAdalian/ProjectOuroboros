package com.perceus.ouroboros;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceus.ouroboros.utilities.PrintUtils;

public class Ouroboros extends JavaPlugin
{
	public static Ouroboros instance;
	
	@Override
	public void onEnable() 
	{
		instance = this;
		PrintUtils.Print("&aOuroboros, ONLINE.");
		Bukkit.getPluginManager().registerEvents(new SkillBoard(), this);
	}
	
	@Override
	public void onDisable() 
	{
		
	}
}
