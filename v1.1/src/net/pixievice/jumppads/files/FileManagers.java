package net.pixievice.jumppads.files;

public class FileManagers {
	
	public void generateLang() {
	
		Lang.setup();
		Lang.get().addDefault("Prefix", "&8[&bPixieJumpPads&8] ");
		Lang.get().addDefault("NoPermissions", "&cYou do not have permission to execute this command!");
		Lang.get().addDefault("NotPlayer", "&cOnly players can execute this command.");
		Lang.get().addDefault("Unknown", "&cUnknown command. &cPlease use &7/PJP Help &cfor a list of commands!");
		Lang.get().addDefault("Create", "&aYou have created a new jump pad named &7%name%&a!");
		Lang.get().addDefault("Remove", "&cYou have renoved a jump pad named &7%name%&c!");
		Lang.get().addDefault("AlreadyExists", "&cThis jump pad already exists!");
		Lang.get().addDefault("NoneExists", "&cThis jump pad does not exits!");
		Lang.get().addDefault("UpdateSound", "&aUpdated the sound to &7%sound%&a!");
		Lang.get().addDefault("UpdateParticle", "&aUpdated the particle to &7%particle%&a!");
		
		Lang.get().options().copyDefaults(true);
		Lang.save();
		
	}
	
	public void generatePads() {
		
		Pads.setup();
		if (Pads.get().getConfigurationSection("JumpPads") == null) {
			Pads.get().createSection("JumpPads");
			Pads.save();
		}
		
	}

}
