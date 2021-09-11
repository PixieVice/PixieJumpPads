package net.pixievice.jumppads;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.pixievice.jumppads.commands.JumpPadCommands;
import net.pixievice.jumppads.files.FileManagers;

public class Main extends JavaPlugin {
	
	private PluginDescriptionFile pluginInfo = getDescription();
	private String pver = pluginInfo.getVersion();
	FileManagers fm = new FileManagers();
	
	@Override
	public void onEnable() {
		
		Bukkit.getLogger().info(ChatUtils.chat("&bPixieJumpPads Enabled!"));
		
		this.getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		fm.generateLang();
		fm.generatePads();
		
		Bukkit.getPluginManager().registerEvents(new Events(this), this);
		getCommand("pjumppad").setExecutor(new JumpPadCommands(this));
		
		
		String ver = getConfig().getString("config-version");
		if (!ver.equals(pver)) {
			Bukkit.getLogger().info(ChatUtils.chat("&cConfig outdated! Please update it!"));
		} else if (ver.equals(pver)) {
			Bukkit.getLogger().info(ChatUtils.chat("&aConfig is up to date!"));
		}
		
	}
	
	@Override
	public void onDisable() {
		
		Bukkit.getLogger().info(ChatUtils.chat("&cPixieJumpPads Disabled!"));
		
	}

}
