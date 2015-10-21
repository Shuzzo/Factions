package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Perm;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;


public class CmdFactionsAccess extends FactionsCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdFactionsAccessView cmdFactionsAccessView = new CmdFactionsAccessView();
	public CmdFactionsAccessPlayer cmdFactionsAccessPlayer = new CmdFactionsAccessPlayer();
	public CmdFactionsAccessFaction cmdFactionsAccessFaction = new CmdFactionsAccessFaction();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsAccess()
	{
		// Children
		this.addChild(this.cmdFactionsAccessView);
		this.addChild(this.cmdFactionsAccessPlayer);
		this.addChild(this.cmdFactionsAccessFaction);
		
		// Aliases
		this.addAliases("access");
		
		// Requirements
		this.addRequirements(ReqIsPlayer.get());
		this.addRequirements(ReqHasPerm.get(Perm.ACCESS.node));
	}
	
}
