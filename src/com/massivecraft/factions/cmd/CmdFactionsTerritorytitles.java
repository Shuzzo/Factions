package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Perm;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.Visibility;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqTitlesAvailable;
import com.massivecraft.massivecore.cmd.type.TypeBoolean;
import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.util.Txt;

public class CmdFactionsTerritorytitles extends FactionsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsTerritorytitles()
	{
		// Aliases
		this.addAliases("tt", "territorytitles");

		// Parameters
		this.addParameter(TypeBoolean.get(), "on|off", "toggle");

		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.TERRITORYTITLES.node));
		this.addRequirements(ReqTitlesAvailable.get());
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Visibility getVisibility()
	{
		// We hide the command if titles aren't available.
		if ( ! Mixin.isTitlesAvailable()) return Visibility.INVISIBLE;
		return super.getVisibility();
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		boolean before = msender.isTerritoryInfoTitles();
		boolean after = this.readArg(!before);
		String desc = Txt.parse(after ? "<g>ON" : "<b>OFF");
		
		// NoChange
		if (after == before)
		{
			msg("<i>Territory titles is already %s<i>.", desc);
			return;
		}
		
		// Apply
		msender.setTerritoryInfoTitles(after);
		
		// Inform
		msg("<i>Territory titles is now %s<i>.", desc);
	}
	
}
