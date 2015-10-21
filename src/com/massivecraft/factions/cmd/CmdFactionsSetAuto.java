package com.massivecraft.factions.cmd;

import java.util.Collections;
import java.util.Set;

import com.massivecraft.factions.Perm;
import com.massivecraft.factions.cmd.type.TypeFaction;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.ps.PS;


public class CmdFactionsSetAuto extends FactionsCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private boolean claim = true;
	public boolean isClaim() { return this.claim; }
	public void setClaim(boolean claim) { this.claim = claim; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsSetAuto(boolean claim)
	{
		// Fields
		this.setClaim(claim);
		
		// Aliases
		this.addAliases("a", "auto");

		// Parameters
		if (claim)
		{
			this.addParameter(TypeFaction.get(), "faction", "you");
		}

		// Requirements
		this.addRequirements(ReqIsPlayer.get());
		String node = claim ? Perm.CLAIM_AUTO.node : Perm.UNCLAIM_AUTO.node;
		this.addRequirements(ReqHasPerm.get(node));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{	
		// Args
		final Faction newFaction;
		if (claim)
		{
			newFaction = this.readArg(msenderFaction);
		}
		else
		{
			newFaction = FactionColl.get().getNone();
		}
		
		// Disable?
		if (newFaction == null || newFaction == msender.getAutoClaimFaction())
		{
			msender.setAutoClaimFaction(null);
			msg("<i>Disabled auto-setting as you walk around.");
			return;
		}
		
		// MPerm Preemptive Check
		if (newFaction.isNormal() && ! MPerm.getPermTerritory().has(msender, newFaction, true)) return;
		
		// Apply / Inform
		msender.setAutoClaimFaction(newFaction);
		msg("<i>Now auto-setting <h>%s<i> land.", newFaction.describeTo(msender));
		
		// Chunks
		final PS chunk = PS.valueOf(me.getLocation()).getChunk(true);
		Set<PS> chunks = Collections.singleton(chunk);		
		
		// Apply / Inform
		msender.tryClaim(newFaction, chunks);
	}
	
}
