package com.massivecraft.factions.cmd;

import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.cmd.MassiveCommand;

public class FactionsCommand extends MassiveCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public MPlayer msender;
	public Faction msenderFaction;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void senderFields(boolean set)
	{
		this.msender = set ? MPlayer.get(sender) : null;
		this.msenderFaction = set ? this.msender.getFaction() : null;
	}
	
}
