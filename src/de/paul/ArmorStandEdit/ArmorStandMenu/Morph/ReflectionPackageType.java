package de.paul.ArmorStandEdit.ArmorStandMenu.Morph;

public enum ReflectionPackageType {
	Server("net.minecraft.server."), CraftBukkit("org.bukkit.craftbukkit.");
	
	public String pack;
	
	private ReflectionPackageType(String pack) {
		this.pack = pack;
	}
}
