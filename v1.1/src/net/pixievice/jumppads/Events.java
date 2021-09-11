package net.pixievice.jumppads;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.pixievice.jumppads.arrays.Arrays;
import net.pixievice.jumppads.managers.PadBreakManager;
import net.pixievice.jumppads.managers.PadJumpManager;

public class Events implements Listener {
	
	PadBreakManager pbm = new PadBreakManager();
	PadJumpManager pjm = new PadJumpManager();
	
	Main main;
	public Events(Main main) {
		this.main = main;
	}
	
    //Jump Pads//
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
    	Player player = e.getPlayer();
    	Material jumppad = Material.matchMaterial(main.getConfig().getString("JumpPads.block"));
    	
    	if (jumppad == e.getBlock().getType()) {
    		pbm.BreakManager(player, e);
     }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
    	
    	Player player = e.getPlayer();
    	Material jumppad = Material.matchMaterial(main.getConfig().getString("JumpPads.block"));
    	Boolean usesound = main.getConfig().getBoolean("JumpPads.jump-sound");
    	Boolean useparticle = main.getConfig().getBoolean("JumpPads.jump-particle");
    	
        if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == jumppad) {
        	pjm.JumpManager(player, e, usesound, useparticle);
        	
        	
        } else if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
        	if (Arrays.jumpers.contains(player.getUniqueId())) {
        		Bukkit.getScheduler ().runTaskLater (main, () -> Arrays.jumpers.remove(player.getUniqueId()), 10);
        	}
        }
    }

}
