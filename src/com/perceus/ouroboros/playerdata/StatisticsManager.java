package com.perceus.ouroboros.playerdata;

import com.google.gson.annotations.Expose;

public class StatisticsManager
{
	@Expose
	private int skillPoints = 0;
	
	@Expose
	private int generalLevel = 0;
	@Expose
	private int movementLevel = 0;
	@Expose
	private int killLevel = 0;
	
	@Expose
	private int generalEXP = 0;
	@Expose
	private int movementEXP = 0;
	@Expose
	private int killEXP = 0;
	
	public int getGeneralEXP()
	{
		return generalEXP;
	}
	public void setGeneralEXP(int generalEXP)
	{
		this.generalEXP = generalEXP;
	}
	
	public int getMovementEXP()
	{
		return movementEXP;
	}
	public void setMovementEXP(int movementEXP)
	{
		this.movementEXP = movementEXP;
	}
	
	public int getKillEXP()
	{
		return killEXP;
	}
	public void setKillEXP(int killEXP)
	{
		this.killEXP = killEXP;
	}
	public int getGeneralLevel()
	{
		return generalLevel;
	}
	public void setGeneralLevel(int generalLevel)
	{
		this.generalLevel = generalLevel;
	}
	public int getMovementLevel()
	{
		return movementLevel;
	}
	public void setMovementLevel(int movementLevel)
	{
		this.movementLevel = movementLevel;
	}
	public int getKillLevel()
	{
		return killLevel;
	}
	public void setKillLevel(int killLevel)
	{
		this.killLevel = killLevel;
	}
	public int getSkillPoints()
	{
		return skillPoints;
	}
	public void setSkillPoints(int skillPoints)
	{
		this.skillPoints = skillPoints;
	}
	
}
