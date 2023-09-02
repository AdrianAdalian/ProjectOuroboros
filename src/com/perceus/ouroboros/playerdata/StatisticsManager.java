package com.perceus.ouroboros.playerdata;

import com.google.gson.annotations.Expose;

public class StatisticsManager
{
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
	
}
