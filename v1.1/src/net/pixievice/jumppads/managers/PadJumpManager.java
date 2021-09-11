package net.pixievice.jumppads.managers;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import net.pixievice.jumppads.arrays.Arrays;
import net.pixievice.jumppads.files.Pads;

public class PadJumpManager {
	
	public void JumpManager(Player player, PlayerMoveEvent e, Boolean effects, Boolean effectp) {
		
    	Set<String> cloc = Pads.get().getConfigurationSection("JumpPads").getKeys(false);
    	for (String loc : cloc) {
    		
    		//Others//
    		String sound = Pads.get().getString("JumpPads." + loc + ".jump-sound");
    		String particle = Pads.get().getString("JumpPads." + loc + ".jump-particle");
    		
    		//Walked on Block//
    		int xBlock = e.getTo().getBlock().getX();
    		int yBlock = e.getTo().getBlock().getY() - 1;
    		int zBlock = e.getTo().getBlock().getZ();
    		World worldBlock = Bukkit.getWorld(e.getTo().getBlock().getWorld().getName());
    		
    		// settings //
    		World world = Bukkit.getWorld(Pads.get().getString("JumpPads." + loc + ".location.world"));
    		int xInt = Pads.get().getInt("JumpPads." + loc + ".location.X");
    		int yInt = Pads.get().getInt("JumpPads." + loc + ".location.Y");
    		int zInt = Pads.get().getInt("JumpPads." + loc + ".location.Z");
    		
    		int force = Pads.get().getInt("JumpPads." + loc + ".force");
    		Double velocity = Pads.get().getDouble("JumpPads." + loc + ".velocity");
    		
    		if (zBlock < 0) { zBlock = zBlock + 1; } if (xBlock < 0) { xBlock = xBlock + 1; }
    		if (worldBlock == world && xInt == xBlock && yInt == yBlock && zInt == zBlock) {
                player.setVelocity(player.getLocation().getDirection().multiply(force));
                player.setVelocity(new Vector(player.getVelocity().getX(), velocity, player.getVelocity().getZ()));
               if (effects == true) { player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0F, 1.0F); }
                if (effectp == true) { player.getWorld().playEffect(player.getLocation(), Effect.valueOf(particle), 20); }
                if (!Arrays.jumpers.contains(player.getUniqueId())) {
                	Arrays.jumpers.add(player.getUniqueId());
    			}
    		}
    	}
		
	}

}
