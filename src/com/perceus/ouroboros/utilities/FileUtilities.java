package com.perceus.ouroboros.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class FileUtilities
{
	private static GsonBuilder gsonBuilder = new GsonBuilder();
	private static Gson gson;
	private static Gson uglygson;
	
	static 
	{
		uglygson = gsonBuilder.disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
		
		gsonBuilder.setPrettyPrinting();
		gson = gsonBuilder.disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	public static Gson getGson() 
	{
		return gson;
	}
	
	public static Gson getUglyGson() 
	{
		return uglygson;
	}
	
	public static <T> T fromFile(String path, Class<T> type) //reading from a new or existing file
			throws FileNotFoundException
	{
		File file = new File(path);
		
		if (!file.exists()) 
		{
			throw new FileNotFoundException("File \"" + file.getName() + "\" doesn't exist");
		}
		
		try (FileReader reader = new FileReader(file))
		{
			return (T) gson.fromJson(reader, type);
		}
		
		catch (IOException | IllegalStateException | JsonSyntaxException e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}

	public static File toFile(String path, Object object) //writing a new or adding to .json file
	{
		BufferedWriter bWriter;
		//Call buffered writer via the variable name bWriter
		File file = new File(path);
		//Creating a new file under the path <PATH>
		try 
		{
			if (!file.exists()) 
			{
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			bWriter = new BufferedWriter(writer);
			
			bWriter.write(gson.toJson(object));
			bWriter.flush();
			
			bWriter.close();
			writer.close();
		}
		/**
		 * Calling FileWriter.class with writer variable as a new FileWriter under the new file we just created.
		 * Creating an instance of BufferedWriter underneath the writer subtext such that bWriter is now calling the method we casted to above.
		 * 
		 * Writing the Json object on the file using the BufferedWriter we initialized above.
		 * Flushing (i.e. clearing the buffer).
		 * 
		 * Finally closing out both the writer and the buffer so that no more data can be written. 
		 */
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		/**
		 * If the file doesn't exist, or there was no string to be written.
		 * Catches the exception if said condition wasn't met above.
		 */
		return file;
		
		// Returns the file so that it can be rewritten in the future, essentially wrapping it in a nice bow :)
	}
	
}
