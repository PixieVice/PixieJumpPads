package net.pixievice.jumppads.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.pixievice.jumppads.ChatUtils;

public class Lang {

	private static File file;
	private static FileConfiguration configFile;
	
	public static void setup() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieJumpPads").getDataFolder(), "lang.yml"); 
		
		if (!file.exists()) {
			try {
			file.createNewFile();
			} catch (IOException e) {
				Bukkit.getLogger().info(ChatUtils.chat(""));
			}
		}
		
		configFile = YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration get() {
		return configFile;
	}
	
	public static void save() {
		try {
			configFile.save(file);
		} catch (IOException e) {
			Bukkit.getLogger().info(ChatUtils.chat("&cUnexpected issue when saving the lang file."));
		}
		
	}
	
	public static void reload() {
		configFile = YamlConfiguration.loadConfiguration(file);
	}
}
