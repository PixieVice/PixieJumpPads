package net.pixievice.jumppads.managers;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import net.pixievice.jumppads.ChatUtils;
import net.pixievice.jumppads.files.Lang;
import net.pixievice.jumppads.files.Pads;

public class PadBreakManager {
	
	public void BreakManager(Player player, BlockBreakEvent e) {
    	String prefix = Lang.get().getString("Prefix");

		Set<String> cloc = Pads.get().getConfigurationSection("JumpPads").getKeys(false);
		for (String loc : cloc) {
		//Walked on Block//
			int xBlock = e.getBlock().getX();
			int yBlock = e.getBlock().getY();
			int zBlock = e.getBlock().getZ();
			World worldBlock = Bukkit.getWorld(e.getBlock().getWorld().getName());
		
		// settings //
    		World world = Bukkit.getWorld(Pads.get().getString("JumpPads." + loc + ".location.world"));
    		int xInt = Pads.get().getInt("JumpPads." + loc + ".location.X");
    		int yInt = Pads.get().getInt("JumpPads." + loc + ".location.Y");
    		int zInt = Pads.get().getInt("JumpPads." + loc + ".location.Z");
    	 //Cord Checker//
    	if (zBlock < 0) { zBlock = zBlock + 1; } if (xBlock < 0) { xBlock = xBlock + 1; }
    	
    	//Block break checker//
		if (worldBlock == world && xInt == xBlock && yInt == yBlock && zInt == zBlock) {
			e.setCancelled(true);
        if (player.hasPermission("pixie.jp.admin")) {
        	 player.sendMessage(ChatUtils.chat(prefix + "&cPlease use &7/PJP remove <name> &cto remove this JumpPad!"));
      }
	 }	
	}
   }

}
