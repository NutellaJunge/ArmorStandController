package de.paul.ArmorStandEdit.ArmorStandMenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
	
	public static ItemStack back = new ItemStack(Material.APPLE);
	
	public static ItemStack move = new ItemStack(Material.COMPASS);
	public static ItemStack tags = new ItemStack(Material.NAME_TAG);
	public static ItemStack head = new ItemStack(Material.PLAYER_HEAD);
	public static ItemStack leftarm = new ItemStack(Material.STICK);
	public static ItemStack rightarm = new ItemStack(Material.STICK);
	public static ItemStack lefthand = new ItemStack(Material.SHIELD);
	public static ItemStack righthand = new ItemStack(Material.SHEARS);
	public static ItemStack body = new ItemStack(Material.OAK_PLANKS);
	public static ItemStack leftleg = new ItemStack(Material.STICK);
	public static ItemStack rightleg = new ItemStack(Material.STICK);
	public static ItemStack remove = new ItemStack(Material.BARRIER);
	public static ItemStack reset = new ItemStack(Material.TNT);
	public static ItemStack copy = new ItemStack(Material.MAP);
	public static ItemStack paste = new ItemStack(Material.MAP);
	public static ItemStack morph = new ItemStack(Material.ZOMBIE_HEAD);
	
	public static ItemStack moveyp = new ItemStack(Material.FIRE_CHARGE);
	public static ItemStack moveyn = new ItemStack(Material.FIRE_CHARGE);
	public static ItemStack movexp = new ItemStack(Material.FIRE_CHARGE);
	public static ItemStack movexn = new ItemStack(Material.FIRE_CHARGE);
	public static ItemStack movezp = new ItemStack(Material.FIRE_CHARGE);
	public static ItemStack movezn = new ItemStack(Material.FIRE_CHARGE);

	public static ItemStack movex = new ItemStack(Material.RED_CONCRETE);
	public static ItemStack movey = new ItemStack(Material.GREEN_CONCRETE);
	public static ItemStack movez = new ItemStack(Material.BLUE_CONCRETE);
	public static ItemStack moverot = new ItemStack(Material.CLOCK);
	
	public static ItemStack moverotright = new ItemStack(Material.SNOWBALL);
	public static ItemStack moverotleft = new ItemStack(Material.SNOWBALL);
	
	public static ItemStack taggravity = new ItemStack(Material.ENDER_EYE);
	public static ItemStack tagarms = new ItemStack(Material.STICK);
	public static ItemStack tagsmall = new ItemStack(Material.GOLD_NUGGET);
	public static ItemStack taginvis = new ItemStack(Material.EXPERIENCE_BOTTLE);
	public static ItemStack tagbase = new ItemStack(Material.STONE);
	public static ItemStack taghead = new ItemStack(Material.PUMPKIN);
	public static ItemStack tagglow = new ItemStack(Material.GLOWSTONE);
	public static ItemStack taginvu = new ItemStack(Material.BEDROCK);
	public static ItemStack tagdis = new ItemStack(Material.CHEST);
	
	public static ItemStack morphzomb = new ItemStack(Material.ROTTEN_FLESH);
	public static ItemStack morphskel = new ItemStack(Material.BONE);
	public static ItemStack morphhusk = new ItemStack(Material.SAND);
	public static ItemStack morphwskel = new ItemStack(Material.COAL);
	public static ItemStack morphstray = new ItemStack(Material.ICE);
	public static ItemStack morphplayer = new ItemStack(Material.PLAYER_HEAD);
	
	public static Inventory MainMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Main Menu");
	public static Inventory MoveMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Menu");
	public static Inventory TagMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Tag Menu");
	public static Inventory MoveHeadMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Head Menu");
	public static Inventory MoveLeftArmMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Left Arm Menu");
	public static Inventory MoveRightArmMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Right Arm Menu");
	public static Inventory MoveLeftLegMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Left Leg Menu");
	public static Inventory MoveRightLegMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Right Leg Menu");
	public static Inventory MoveBodyMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Body Menu");
	public static Inventory MorphMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Morph Menu");
	
	ArmorStand ar;
	Player p;
	
	public Menu(ArmorStand ar, Player p) {
		this.ar = ar;
		this.p = p;
		ar.getWorld().spawnParticle(Particle.FLAME, ar.getLocation(), 40, 0, 1, 0, 0.02);
		register();
		openMainMenu(p);
	}
	
	private void register() {
		ItemMeta m = back.getItemMeta();
		m.setDisplayName("§cBack");
		back.setItemMeta(m);
		
		m = move.getItemMeta();
		m.setDisplayName("§7Movement");
		move.setItemMeta(m);
		
		m = morph.getItemMeta();
		m.setDisplayName("§2Morph");
		morph.setItemMeta(m);
		
		m = tags.getItemMeta();
		m.setDisplayName("§9Tags");
		tags.setItemMeta(m);
		
		m = head.getItemMeta();
		m.setDisplayName("§eMove Head");
		head.setItemMeta(m);
		
		m = leftarm.getItemMeta();
		m.setDisplayName("§eMove Left Arm");
		leftarm.setItemMeta(m);
		
		m = rightarm.getItemMeta();
		m.setDisplayName("§eMove Right Arm");
		rightarm.setItemMeta(m);
		
		m = lefthand.getItemMeta();
		m.setDisplayName("§3Chose Left Hand");
		lefthand.setItemMeta(m);
		
		m = righthand.getItemMeta();
		m.setDisplayName("§3Chose Right Hand");
		righthand.setItemMeta(m);
		
		m = body.getItemMeta();
		m.setDisplayName("§eMove Body");
		body.setItemMeta(m);
		
		m = leftleg.getItemMeta();
		m.setDisplayName("§eChose Left Leg");
		leftleg.setItemMeta(m);
		
		m = rightleg.getItemMeta();
		m.setDisplayName("§eChose Right Leg");
		rightleg.setItemMeta(m);
		
		m = remove.getItemMeta();
		m.setDisplayName("§cRemove");
		remove.setItemMeta(m);
		
		m = reset.getItemMeta();
		m.setDisplayName("§4Reset");
		reset.setItemMeta(m);
		
		m = copy.getItemMeta();
		m.setDisplayName("§5Copy");
		copy.setItemMeta(m);
		
		m = paste.getItemMeta();
		m.setDisplayName("§5Paste");
		paste.setItemMeta(m);
		
		//__________________________________
		
		MainMenu.setItem((9 * 3 - 1) - 7, move);
		MainMenu.setItem((9 * 3 - 1) - 1, tags);
		MainMenu.setItem((9 * 1 - 1) - 4, head);
		MainMenu.setItem((9 * 3 - 1) - 4, body);
		MainMenu.setItem((9 * 2 - 1) - 5, leftarm);
		MainMenu.setItem((9 * 2 - 1) - 3, rightarm);
		if (ar.hasArms()) {
			MainMenu.setItem((9 * 3 - 1) - 5, lefthand);
			MainMenu.setItem((9 * 3 - 1) - 3, righthand);
		} else {
			MainMenu.setItem((9 * 3 - 1) - 5, new ItemStack(Material.AIR));
			MainMenu.setItem((9 * 3 - 1) - 3, new ItemStack(Material.AIR));
		}
		MainMenu.setItem((9 * 4 - 1) - 5, leftleg);
		MainMenu.setItem((9 * 4 - 1) - 3, rightleg);
		MainMenu.setItem((9 * 5 - 1) - 4, remove);
		MainMenu.setItem((9 * 5 - 1) - 3, reset);
		MainMenu.setItem((9 * 5 - 1) - 5, paste);
		MainMenu.setItem((9 * 5 - 1) - 6, copy);
		MainMenu.setItem(getSlot(4, 7), morph);
		
		//__________________________________
		
		m = morphzomb.getItemMeta();
		m.setDisplayName("§2Morph to Zombie");
		morphzomb.setItemMeta(m);
		
		m = morphskel.getItemMeta();
		m.setDisplayName("§7Morph to Skeleton");
		morphskel.setItemMeta(m);
		
		m = morphhusk.getItemMeta();
		m.setDisplayName("§eMorph to Husk");
		morphhusk.setItemMeta(m);
		
		m = morphwskel.getItemMeta();
		m.setDisplayName("§8Morph to Wither Skeleton");
		morphwskel.setItemMeta(m);
		
		m = morphstray.getItemMeta();
		m.setDisplayName("§3Morph to Stray");
		morphstray.setItemMeta(m);
		
		m = morphplayer.getItemMeta();
		m.setDisplayName("§6Morph to Player §4(WIP)");
		morphplayer.setItemMeta(m);
		
		//__________________________________
		
		MorphMenu.setItem(getSlot(2, 2), morphzomb);
		MorphMenu.setItem(getSlot(2, 3), morphhusk);
		MorphMenu.setItem(getSlot(2, 4), morphplayer);
		MorphMenu.setItem(getSlot(2, 5), morphskel);
		MorphMenu.setItem(getSlot(2, 6), morphstray);
		MorphMenu.setItem(getSlot(2, 7), morphwskel);
		
		//__________________________________
		
		m = movexp.getItemMeta();
		m.setDisplayName("§cMove-X-Positiv");
		movexp.setItemMeta(m);
		
		m = movexn.getItemMeta();
		m.setDisplayName("§cMove-X-Negativ");
		movexn.setItemMeta(m);
		
		m = moveyp.getItemMeta();
		m.setDisplayName("§aMove-Y-Positiv");
		moveyp.setItemMeta(m);
		
		m = moveyn.getItemMeta();
		m.setDisplayName("§aMove-Y-Negativ");
		moveyn.setItemMeta(m);
		
		m = movezp.getItemMeta();
		m.setDisplayName("§9Move-Z-Positiv");
		movezp.setItemMeta(m);
		
		m = movezn.getItemMeta();
		m.setDisplayName("§9Move-Z-Negativ");
		movezn.setItemMeta(m);
		
		m = movex.getItemMeta();
		m.setDisplayName("§cMove-X");
		movex.setItemMeta(m);
		
		m = movey.getItemMeta();
		m.setDisplayName("§aMove-Y");
		movey.setItemMeta(m);
		
		m = movez.getItemMeta();
		m.setDisplayName("§9Move-Z");
		movez.setItemMeta(m);
		
		m = moverot.getItemMeta();
		m.setDisplayName("§eRotate");
		moverot.setItemMeta(m);
		
		m = moverotleft.getItemMeta();
		m.setDisplayName("§eRotate-Left");
		moverotleft.setItemMeta(m);
		
		m = moverotright.getItemMeta();
		m.setDisplayName("§eRotate-Right");
		moverotright.setItemMeta(m);
		
		//_________________________________
		
		MoveMenu.setItem(getSlot(1, 2), movexp);
		MoveMenu.setItem(getSlot(1, 5), moveyp);
		MoveMenu.setItem(getSlot(1, 8), movezp);
		MoveMenu.setItem(getSlot(2, 2), movex);
		MoveMenu.setItem(getSlot(2, 5), movey);
		MoveMenu.setItem(getSlot(2, 8), movez);
		MoveMenu.setItem(getSlot(3, 2), movexn);
		MoveMenu.setItem(getSlot(3, 5), moveyn);
		MoveMenu.setItem(getSlot(3, 8), movezn);
		
		MoveMenu.setItem(getSlot(4, 4), moverotleft);
		MoveMenu.setItem(getSlot(4, 5), moverot);
		MoveMenu.setItem(getSlot(4, 6), moverotright);
		
		MoveMenu.setItem(getSlot(4, 1), back);
		
		//_________________________________
		
		MoveHeadMenu.setItem(getSlot(1, 2), movexp);
		MoveHeadMenu.setItem(getSlot(1, 5), moveyp);
		MoveHeadMenu.setItem(getSlot(1, 8), movezp);
		MoveHeadMenu.setItem(getSlot(2, 2), movex);
		MoveHeadMenu.setItem(getSlot(2, 5), movey);
		MoveHeadMenu.setItem(getSlot(2, 8), movez);
		MoveHeadMenu.setItem(getSlot(3, 2), movexn);
		MoveHeadMenu.setItem(getSlot(3, 5), moveyn);
		MoveHeadMenu.setItem(getSlot(3, 8), movezn);
		
		MoveHeadMenu.setItem(getSlot(4, 1), back);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveLeftArmMenu.setItem(getSlot(1, 2), movexp);
		MoveLeftArmMenu.setItem(getSlot(1, 5), moveyp);
		MoveLeftArmMenu.setItem(getSlot(1, 8), movezp);
		MoveLeftArmMenu.setItem(getSlot(2, 2), movex);
		MoveLeftArmMenu.setItem(getSlot(2, 5), movey);
		MoveLeftArmMenu.setItem(getSlot(2, 8), movez);
		MoveLeftArmMenu.setItem(getSlot(3, 2), movexn);
		MoveLeftArmMenu.setItem(getSlot(3, 5), moveyn);
		MoveLeftArmMenu.setItem(getSlot(3, 8), movezn);
		
		MoveLeftArmMenu.setItem(getSlot(4, 1), back);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveRightArmMenu.setItem(getSlot(1, 2), movexp);
		MoveRightArmMenu.setItem(getSlot(1, 5), moveyp);
		MoveRightArmMenu.setItem(getSlot(1, 8), movezp);
		MoveRightArmMenu.setItem(getSlot(2, 2), movex);
		MoveRightArmMenu.setItem(getSlot(2, 5), movey);
		MoveRightArmMenu.setItem(getSlot(2, 8), movez);
		MoveRightArmMenu.setItem(getSlot(3, 2), movexn);
		MoveRightArmMenu.setItem(getSlot(3, 5), moveyn);
		MoveRightArmMenu.setItem(getSlot(3, 8), movezn);
		
		MoveRightArmMenu.setItem(getSlot(4, 1), back);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveBodyMenu.setItem(getSlot(1, 2), movexp);
		MoveBodyMenu.setItem(getSlot(1, 5), moveyp);
		MoveBodyMenu.setItem(getSlot(1, 8), movezp);
		MoveBodyMenu.setItem(getSlot(2, 2), movex);
		MoveBodyMenu.setItem(getSlot(2, 5), movey);
		MoveBodyMenu.setItem(getSlot(2, 8), movez);
		MoveBodyMenu.setItem(getSlot(3, 2), movexn);
		MoveBodyMenu.setItem(getSlot(3, 5), moveyn);
		MoveBodyMenu.setItem(getSlot(3, 8), movezn);
		
		MoveBodyMenu.setItem(getSlot(4, 1), back);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveLeftLegMenu.setItem(getSlot(0, 2), movexp);
		MoveLeftLegMenu.setItem(getSlot(0, 5), moveyp);
		MoveLeftLegMenu.setItem(getSlot(0, 8), movezp);
		MoveLeftLegMenu.setItem(getSlot(1, 2), movex);
		MoveLeftLegMenu.setItem(getSlot(1, 5), movey);
		MoveLeftLegMenu.setItem(getSlot(1, 8), movez);
		MoveLeftLegMenu.setItem(getSlot(2, 2), movexn);
		MoveLeftLegMenu.setItem(getSlot(2, 5), moveyn);
		MoveLeftLegMenu.setItem(getSlot(2, 8), movezn);
		
		MoveLeftLegMenu.setItem(getSlot(4, 1), back);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveRightLegMenu.setItem(getSlot(0, 2), movexp);
		MoveRightLegMenu.setItem(getSlot(0, 5), moveyp);
		MoveRightLegMenu.setItem(getSlot(0, 8), movezp);
		MoveRightLegMenu.setItem(getSlot(1, 2), movex);
		MoveRightLegMenu.setItem(getSlot(1, 5), movey);
		MoveRightLegMenu.setItem(getSlot(1, 8), movez);
		MoveRightLegMenu.setItem(getSlot(2, 2), movexn);
		MoveRightLegMenu.setItem(getSlot(2, 5), moveyn);
		MoveRightLegMenu.setItem(getSlot(2, 8), movezn);
		
		MoveRightLegMenu.setItem(getSlot(4, 1), back);
		
		//_________________________________
		
		m = taggravity.getItemMeta();
		m.setDisplayName("§dGravity: " + this.ar.hasGravity());
		taggravity.setItemMeta(m);
		
		m = tagarms.getItemMeta();
		m.setDisplayName("§bArms: " + this.ar.hasArms());
		tagarms.setItemMeta(m);
		
		m = tagsmall.getItemMeta();
		m.setDisplayName("§2Small: " + this.ar.isSmall());
		tagsmall.setItemMeta(m);
		
		m = tagarms.getItemMeta();
		m.setDisplayName("§aInvisible: " + !this.ar.isVisible());
		taginvis.setItemMeta(m);
		
		m = tagbase.getItemMeta();
		m.setDisplayName("§5Base Plate: " + this.ar.hasBasePlate());
		tagbase.setItemMeta(m);
		
		m = taghead.getItemMeta();
		m.setDisplayName("§3Head: " + this.ar.getHelmet().getType().name().toUpperCase());
		taghead.setItemMeta(m);
		
		m = tagglow.getItemMeta();
		m.setDisplayName("§aGlowing: " + this.ar.isGlowing());
		tagglow.setItemMeta(m);
		
		m = taginvu.getItemMeta();
		m.setDisplayName("§9Indestructible: " + this.ar.isInvulnerable());
		taginvu.setItemMeta(m);
		
		m = tagdis.getItemMeta();
		m.setDisplayName("§4Slots blocked: " + !this.ar.isCollidable());
		tagdis.setItemMeta(m);
		
		//_________________________________
		
		TagMenu.setItem((9 * 2 - 1) - 7, taggravity);
		TagMenu.setItem((9 * 3 - 1) - 7, tagarms);
		TagMenu.setItem((9 * 2 - 1) - 5, tagsmall);
		TagMenu.setItem((9 * 3 - 1) - 5, taginvis);
		TagMenu.setItem((9 * 2 - 1) - 3, tagbase);
		TagMenu.setItem((9 * 3 - 1) - 3, taghead);
		TagMenu.setItem((9 * 2 - 1) - 1, tagglow);
		TagMenu.setItem((9 * 3 - 1) - 1, taginvu);
		TagMenu.setItem(getSlot(3, 2), tagdis);
		
		TagMenu.setItem(getSlot(4, 1), back);
		
	}

	public void openMainMenu(Player p) {
		p.closeInventory();
		p.openInventory(MainMenu);
	}
	
	public void openTagMenu(Player p) {
		p.closeInventory();
		p.openInventory(TagMenu);
	}
	
	public void openMoveMenu(Player p) {
		p.closeInventory();
		p.openInventory(MoveMenu);
	}
	
	public void openMoveHeadMenu(Player p) {
		p.closeInventory();
		p.openInventory(MoveHeadMenu);
	}
	
	public void openMoveBodyMenu(Player p) {
		p.closeInventory();
		p.openInventory(MoveBodyMenu);
	}
	
	public void openMoveLeftArmMenu(Player p) {
		p.closeInventory();
		p.openInventory(MoveLeftArmMenu);
	}
	
	public void openMoveRightArmMenu(Player p) {
		p.closeInventory();
		p.openInventory(MoveRightArmMenu);
	}
	
	public void openMoveLeftLegMenu(Player p) {
		p.closeInventory();
		p.openInventory(MoveLeftLegMenu);
	}
	
	public void openMoveRightLegMenu(Player p) {
		p.closeInventory();
		p.openInventory(MoveRightLegMenu);
	}
	
	public void openMorphMenu(Player p) {
		p.closeInventory();
		p.openInventory(MorphMenu);
	}
	
	public ArmorStand getArmorStand() {
		return ar;
	}
	
	public static int getSlot(int row, int column) {
		int pose = (9*row-1);
		pose = pose - (column * -1);
		return pose;
	}
}
