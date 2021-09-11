package net.pixievice.jumppads.managers;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pixievice.jumppads.ChatUtils;
import net.pixievice.jumppads.files.Pads;

public class PadManager {
	
	public void createData(Player player, String name, int force, int velocity) {
		
		Double px = player.getLocation().getX();
		Double py = player.getLocation().getY() - 1;
		Double pz = player.getLocation().getZ();
		
		Pads.get().set("JumpPads." + name + ".force", force);
		Pads.get().set("JumpPads." + name + ".velocity", 0.0 + velocity);
		retrieveSound(name);
		Pads.get().set("JumpPads." + name + ".jump-particle", "EXPLOSION");
		Pads.get().set("JumpPads." + name + ".location.X", px);
		Pads.get().set("JumpPads." + name + ".location.Y", py);
		Pads.get().set("JumpPads." + name + ".location.Z", pz);
		Pads.get().set("JumpPads." + name + ".location.world", player.getWorld().getName());
		Pads.save();
		
	}
	
	public void addBlock(Player player, String name, Material block) {
		
		Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());
		player.getWorld().getBlockAt(loc).setType(block);
		
	}
	
	public void removeBlock(String name) {
		
		Double x = Pads.get().getDouble("JumpPads." + name + ".location.X");
		Double y = Pads.get().getDouble("JumpPads." + name + ".location.Y");
		Double z = Pads.get().getDouble("JumpPads." + name + ".location.Z");
		World world = Bukkit.getWorld(Pads.get().getString("JumpPads." + name + ".location.world"));
		Location loc = new Location(world, x, y, z);
		
		world.getBlockAt(loc).setType(Material.AIR);
		Pads.get().set("JumpPads." + name, null);
		Pads.save();
		
	}
	
	public void listPads(CommandSender sender) {
		
		Set<String> pads = Pads.get().getConfigurationSection("JumpPads").getKeys(false);
		sender.sendMessage(ChatUtils.chat("&aJumpPads:"));
		for (String list : pads) {
			sender.sendMessage(ChatUtils.chat("&7- &d" + list));
		}
		
	}
	
	public void modifySound(String name, String sound) {
		
		Pads.get().set("JumpPads." + name + ".jump-sound", sound);
		Pads.save();
		
	}
	
	public void modifyParticle(String name, String particle) {
		
		Pads.get().set("JumpPads." + name + ".jump-particle", particle);
		Pads.save();
		
	}

	public void modifyPower(String name, int force, int velocity) {
		
		Pads.get().set("JumpPads." + name + ".force", force);
		Pads.get().set("JumpPads." + name + ".velocity", velocity);
		Pads.save();
		
	}
	
	public void retrieveSound(String name) {
		
		if (Bukkit.getVersion().contains("1.8")) {
			Pads.get().set("JumpPads." + name + ".jump-sound", "BAT_TAKEOFF");
		} else {
			Pads.get().set("JumpPads." + name + ".jump-sound", "BLOCK_GLASS_BREAK");
		}
		
	}

}
