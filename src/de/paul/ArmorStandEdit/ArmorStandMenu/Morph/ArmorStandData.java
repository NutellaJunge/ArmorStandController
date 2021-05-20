package de.paul.ArmorStandEdit.ArmorStandMenu.Morph;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public class ArmorStandData {
	
	private ItemStack boots;
	private ItemStack leggings;
	private ItemStack chestplate;
	private ItemStack helmet;
	
	private ItemStack mainHand;
	private ItemStack offHand;
	
	private boolean glowing;
	private boolean gravity;
	private boolean invulnerable;
	private boolean collidable;
	
	private Location loc;
	
	public ArmorStandData() {
		
	}

	public ArmorStandData(ArmorStand ar) {
		boots = ar.getEquipment().getBoots();
		leggings = ar.getEquipment().getLeggings();
		chestplate = ar.getEquipment().getChestplate();
		helmet = ar.getEquipment().getHelmet();
		
		mainHand = ar.getEquipment().getItemInMainHand();
		offHand = ar.getEquipment().getItemInOffHand();
		
		glowing = ar.isGlowing();
		gravity = ar.hasGravity();
		invulnerable = ar.isInvulnerable();
		collidable = ar.isCollidable();
		
		loc = ar.getLocation();
	}

	public ItemStack getBoots() {
		return boots;
	}
	
	public ItemStack getChestplate() {
		return chestplate;
	}
	
	public ItemStack getLeggings() {
		return leggings;
	}
	
	public ItemStack getHelmet() {
		return helmet;
	}
	
	public ItemStack getMainHand() {
		return mainHand;
	}
	
	public ItemStack getOffHand() {
		return offHand;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
	
	public boolean isGlowing() {
		return glowing;
	}
	
	public boolean hasGravity() {
		return gravity;
	}
	
	public boolean isInvulnerable() {
		return invulnerable;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public void setBoots(ItemStack boots) {
		this.boots = boots;
	}
	
	public void setChestplate(ItemStack chestplate) {
		this.chestplate = chestplate;
	}
	
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}
	
	public void setGlowing(boolean glowing) {
		this.glowing = glowing;
	}
	
	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}
	
	public void setHelmet(ItemStack helmet) {
		this.helmet = helmet;
	}
	
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}
	
	public void setLeggings(ItemStack leggings) {
		this.leggings = leggings;
	}
	
	public void setLocation(Location loc) {
		this.loc = loc;
	}
	
	public void setMainHand(ItemStack mainHand) {
		this.mainHand = mainHand;
	}
	
	public void setOffHand(ItemStack offHand) {
		this.offHand = offHand;
	}

	public void apply(ArmorStand ar) {
		ar.getEquipment().setBoots(getBoots());
		ar.getEquipment().setChestplate(getChestplate());
		ar.setGlowing(isGlowing());
		ar.setGravity(hasGravity());
		ar.getEquipment().setHelmet(getHelmet());
		ar.setInvulnerable(isInvulnerable());
		ar.getEquipment().setItemInMainHand(getMainHand());
		ar.getEquipment().setItemInOffHand(getOffHand());
		ar.getEquipment().setLeggings(getLeggings());
		ar.setCollidable(isCollidable());
		ar.teleport(getLocation());
	}
}
