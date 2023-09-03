package com.perceus.ouroboros.playerdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.perceus.ouroboros.Ouroboros;
import com.perceus.ouroboros.utilities.FileUtilities;

public class PlayerDataFileHolder
{
	private static Map<UUID, StatisticsManager> player_registry = new HashMap<>();

	public static StatisticsManager getPlayerData(UUID uuid) throws FileNotFoundException
	{
		if (player_registry.containsKey(uuid)) 
		{
			return player_registry.get(uuid);
		}
		
		
		String fileString = Ouroboros.instance.getDataFolder().toString();
		
		StatisticsManager data;
		
		File file = new File(fileString + "/playerdata/data/" + uuid.toString()+ ".json");
		
		if (!file.exists()) 
		{
			data = new StatisticsManager();
			FileUtilities.toFile(file.getAbsolutePath(), data);
			player_registry.put(uuid, data);
			return data;
		}
		
		if (file.exists() && !file.canRead()) 
		{
			data = new StatisticsManager();
			FileUtilities.toFile(file.getAbsolutePath(), data);
			player_registry.put(uuid, data);
			return data;
		}
		
		data = FileUtilities.fromFile(file.getAbsolutePath(), StatisticsManager.class);
		player_registry.put(uuid, data);
		return data;
	}
	
	public static File getPath(UUID uuid) 
	{
		String fileString = Ouroboros.instance.getDataFolder().toString();
		File file = new File(fileString + "/playerdata/data/" + uuid.toString() + ".json");
		return file.getAbsoluteFile();
	}
}
