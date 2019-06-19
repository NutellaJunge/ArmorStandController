package pack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
	
	public static ItemStack move = new ItemStack(Material.COMPASS);
	public static ItemStack tags = new ItemStack(Material.NAME_TAG);
	public static ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	public static ItemStack leftarm = new ItemStack(Material.STICK);
	public static ItemStack rightarm = new ItemStack(Material.STICK);
	public static ItemStack lefthand = new ItemStack(Material.SHIELD);
	public static ItemStack righthand = new ItemStack(Material.SHEARS);
	public static ItemStack body = new ItemStack(Material.WOOD);
	public static ItemStack leftleg = new ItemStack(Material.STICK);
	public static ItemStack rightleg = new ItemStack(Material.STICK);
	public static ItemStack remove = new ItemStack(Material.BARRIER);
	public static ItemStack reset = new ItemStack(Material.TNT);
	
	public static ItemStack moveup = new ItemStack(Material.ARROW);
	public static ItemStack movedown = new ItemStack(Material.ARROW);
	public static ItemStack moverotright = new ItemStack(Material.ARROW);
	public static ItemStack moverotleft = new ItemStack(Material.ARROW);
	
	public static ItemStack moveright = new ItemStack(Material.ARROW);
	public static ItemStack moveleft = new ItemStack(Material.ARROW);
	public static ItemStack movevor = new ItemStack(Material.ARROW);
	public static ItemStack moveback = new ItemStack(Material.ARROW);
	
	public static ItemStack taggravity = new ItemStack(Material.EYE_OF_ENDER);
	public static ItemStack tagarms = new ItemStack(Material.STICK);
	public static ItemStack tagsmall = new ItemStack(Material.GOLD_NUGGET);
	public static ItemStack taginvis = new ItemStack(Material.EXP_BOTTLE);
	public static ItemStack tagbase = new ItemStack(Material.STONE);
	public static ItemStack taghead = new ItemStack(Material.PUMPKIN);
	public static ItemStack tagglow = new ItemStack(Material.GLOWSTONE);
	public static ItemStack taginvu = new ItemStack(Material.BEDROCK);
	
	public static Inventory MainMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Main Menu");
	public static Inventory MoveMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Menu");
	public static Inventory TagMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Tag Menu");
	public static Inventory MoveHeadMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Head Menu");
	public static Inventory MoveLeftArmMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Left Arm Menu");
	public static Inventory MoveRightArmMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Right Arm Menu");
	public static Inventory MoveLeftLegMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Left Leg Menu");
	public static Inventory MoveRightLegMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Right Leg Menu");
	public static Inventory MoveBodyMenu = Bukkit.createInventory(null, 9 * 5, "§5Controller §6| Move Body Menu");
	
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
		ItemMeta m = move.getItemMeta();
		m.setDisplayName("§7Movement");
		move.setItemMeta(m);
		
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
		m.setDisplayName("§3Move Left Hand");
		lefthand.setItemMeta(m);
		
		m = righthand.getItemMeta();
		m.setDisplayName("§3Move Right Hand");
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
			MainMenu.setItem((9 * 3 - 1) - 5, new ItemStack(0));
			MainMenu.setItem((9 * 3 - 1) - 3, new ItemStack(0));
		}
		MainMenu.setItem((9 * 4 - 1) - 5, leftleg);
		MainMenu.setItem((9 * 4 - 1) - 3, rightleg);
		MainMenu.setItem((9 * 5 - 1) - 4, remove);
		MainMenu.setItem((9 * 5 - 1) - 3, reset);
		
		//__________________________________
		
		m = moveleft.getItemMeta();
		m.setDisplayName("§eMove Left");
		moveleft.setItemMeta(m);
		
		m = moveright.getItemMeta();
		m.setDisplayName("§eMove Right");
		moveright.setItemMeta(m);
		
		m = movevor.getItemMeta();
		m.setDisplayName("§eMove in Front");
		movevor.setItemMeta(m);
		
		m = moveback.getItemMeta();
		m.setDisplayName("§eMove Back");
		moveback.setItemMeta(m);
		
		//__________________________________
		
		MoveHeadMenu.setItem((9 * 2 - 1) - 4, movevor);
		MoveHeadMenu.setItem((9 * 4 - 1) - 4, moveback);
		MoveHeadMenu.setItem((9 * 3 - 1) - 5, moveleft);
		MoveHeadMenu.setItem((9 * 3 - 1) - 3, moveright);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveBodyMenu.setItem((9 * 2 - 1) - 4, movevor);
		MoveBodyMenu.setItem((9 * 4 - 1) - 4, moveback);
		MoveBodyMenu.setItem((9 * 3 - 1) - 5, moveleft);
		MoveBodyMenu.setItem((9 * 3 - 1) - 3, moveright);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveLeftArmMenu.setItem((9 * 2 - 1) - 4, movevor);
		MoveLeftArmMenu.setItem((9 * 4 - 1) - 4, moveback);
		MoveLeftArmMenu.setItem((9 * 3 - 1) - 5, moveleft);
		MoveLeftArmMenu.setItem((9 * 3 - 1) - 3, moveright);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveRightArmMenu.setItem((9 * 2 - 1) - 4, movevor);
		MoveRightArmMenu.setItem((9 * 4 - 1) - 4, moveback);
		MoveRightArmMenu.setItem((9 * 3 - 1) - 5, moveleft);
		MoveRightArmMenu.setItem((9 * 3 - 1) - 3, moveright);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveLeftLegMenu.setItem((9 * 2 - 1) - 4, movevor);
		MoveLeftLegMenu.setItem((9 * 4 - 1) - 4, moveback);
		MoveLeftLegMenu.setItem((9 * 3 - 1) - 5, moveleft);
		MoveLeftLegMenu.setItem((9 * 3 - 1) - 3, moveright);
		
		//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
		MoveRightLegMenu.setItem((9 * 2 - 1) - 4, movevor);
		MoveRightLegMenu.setItem((9 * 4 - 1) - 4, moveback);
		MoveRightLegMenu.setItem((9 * 3 - 1) - 5, moveleft);
		MoveRightLegMenu.setItem((9 * 3 - 1) - 3, moveright);
		
		//__________________________________
		
		m = moveup.getItemMeta();
		m.setDisplayName("§eMove-Up");
		moveup.setItemMeta(m);
		
		m = movedown.getItemMeta();
		m.setDisplayName("§eMove-Down");
		movedown.setItemMeta(m);
		
		m = moverotleft.getItemMeta();
		m.setDisplayName("§eRotate-Left");
		moverotleft.setItemMeta(m);
		
		m = moverotright.getItemMeta();
		m.setDisplayName("§eRotate-Right");
		moverotright.setItemMeta(m);
		
		//_________________________________
		
		MoveMenu.setItem((9 * 2 - 1) - 4, moveup);
		MoveMenu.setItem((9 * 4 - 1) - 4, movedown);
		MoveMenu.setItem((9 * 3 - 1) - 5, moverotleft);
		MoveMenu.setItem((9 * 3 - 1) - 3, moverotright);
		
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
		
		//_________________________________
		
		TagMenu.setItem((9 * 2 - 1) - 7, taggravity);
		TagMenu.setItem((9 * 3 - 1) - 7, tagarms);
		TagMenu.setItem((9 * 2 - 1) - 5, tagsmall);
		TagMenu.setItem((9 * 3 - 1) - 5, taginvis);
		TagMenu.setItem((9 * 2 - 1) - 3, tagbase);
		TagMenu.setItem((9 * 3 - 1) - 3, taghead);
		TagMenu.setItem((9 * 2 - 1) - 1, tagglow);
		TagMenu.setItem((9 * 3 - 1) - 1, taginvu);
		
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
	
	public ArmorStand getArmorStand() {
		return ar;
	}
}
