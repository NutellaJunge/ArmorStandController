package de.paul.ArmorStandEdit.ArmorStandMenu.Morph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.plaf.metal.MetalPopupMenuSeparatorUI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.paul.ArmorStandEdit.Config;
import de.paul.ArmorStandEdit.JsonItemStack;
import de.paul.ArmorStandEdit.main;
import de.paul.ArmorStandEdit.ArmorStandMenu.Menu;

public class Morph implements Listener {
	
	public static HashMap<LivingEntity, ArmorStandData> morphsEntity = new HashMap<>();
	public static HashMap<Player, LivingEntity> menusEntity = new HashMap<>();
	
	public static HashMap<FakePlayer, ArmorStandData> morphsFakePlayer = new HashMap<>();
	public static HashMap<Player, FakePlayer> menusFakePlayer = new HashMap<>();
	
	public static ItemStack remove = Menu.remove;
	public static ItemStack head = new ItemStack(Material.LEATHER_HELMET);
	public static ItemStack legins = new ItemStack(Material.LEATHER_LEGGINGS);
	public static ItemStack body = new ItemStack(Material.LEATHER_CHESTPLATE);
	public static ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	public static ItemStack lefthand = Menu.lefthand;
	public static ItemStack righthand = Menu.righthand;
	
	public static Inventory morphMenu = Bukkit.createInventory(null, 9*5, "§5Controller §6| Morph Menu");

	public static void morph(Player p, ArmorStand ar, EntityType et) {
		if (et != EntityType.PLAYER) {
			LivingEntity le = (LivingEntity) ar.getWorld().spawnEntity(ar.getLocation(), et);
			morphsEntity.put(le, new ArmorStandData(ar));
			ar.setVisible(false);
			ar.remove();
			
			le.setAI(false);
			le.setSilent(true);
			le.setInvulnerable(true);
			le.getEquipment().clear();
			le.setCanPickupItems(false);
			
			copyAttributesToMorph(le, new ArmorStandData(ar));
		} else {
			new FakePlayerNameMenu(p);
			Bukkit.getScheduler().runTaskTimer(main.instance, new Runnable() {
				
				@Override
				public void run() {
					if (FakePlayerNameMenu.data.containsKey(p)) {
						p.closeInventory();
						String name = FakePlayerNameMenu.data.get(p);
						FakePlayerNameMenu.data.remove(p);
						FakePlayer fp = new FakePlayer(name, "§4", ar.getLocation());
						fp.setAttributes(new ArmorStandData(ar));
						morphsFakePlayer.put(fp, new ArmorStandData(ar));
						ar.setVisible(false);
						ar.remove();
					}
				}
			}, 0, 10);
		}
	}
	
	public static void morph(String p, ArmorStandData ar, EntityType et) {
		if (et != EntityType.PLAYER) {
			LivingEntity le = (LivingEntity) ar.getLocation().getWorld().spawnEntity(ar.getLocation(), et);
			morphsEntity.put(le, ar);
			
			le.setAI(false);
			le.setSilent(true);
			le.setInvulnerable(true);
			le.getEquipment().clear();
			le.setCanPickupItems(false);
			
			copyAttributesToMorph(le, ar);
		} else {
			FakePlayer fp = new FakePlayer(p, "§4", ar.getLocation());
			fp.setAttributes(ar);
			morphsFakePlayer.put(fp, ar);
		}
	}
	
	public static void saveToConfig(Config c) {
		JSONArray morphs = (JSONArray) c.get("morphs");
		
		if (morphs == null) {
			morphs = new JSONArray();
		}
		
		for (LivingEntity ent : morphsEntity.keySet()) {
			JSONObject o = new JSONObject();
			o.put("Type", ent.getType().getName());
			o.put("FakePlayerSkinName", null);
			ArmorStandData as = morphsEntity.get(ent);
			copyAttributesToArmorStandData(as, ent);
			o.put("ArmorStand", ArmorStandDataToJSON(as));
			morphs.add(o);
			
			ent.remove();
		}
		
		for (FakePlayer fp : morphsFakePlayer.keySet()) {
			JSONObject o = new JSONObject();
			o.put("Type", "Player");
			o.put("FakePlayerSkinName", fp.getSkinName());
			o.put("SkinName", fp.getSkinName());
			ArmorStandData as = morphsFakePlayer.get(fp);
			copyAttributesToArmorStandData(as, fp);
			o.put("ArmorStand", ArmorStandDataToJSON(as));
			morphs.add(o);
			
			fp.destroy();
		}
		
		c.set("morphs", morphs);
	}
	
	public static void loadFromConfig(Config c) {
		JSONArray morphs = (JSONArray) c.get("morphs");
		
		if (morphs == null) {
			morphs = new JSONArray();
		}
		
		for (Object jo : morphs) {
			JSONObject o = (JSONObject) jo;
			morph((String) o.get("FakePlayerSkinName"), getArmorStandDataFromJSON((JSONObject) o.get("ArmorStand")), EntityType.fromName((String) o.get("Type")));
		}
		
		c.set("morphs", new JSONArray());
	}
	
	private static JSONObject ArmorStandDataToJSON(ArmorStandData as) {
		Config o = new Config(new JSONObject());
		o.set("Boots", JsonItemStack.toJson(as.getBoots()));
		o.set("Leggings", JsonItemStack.toJson(as.getLeggings()));
		o.set("Chestplate", JsonItemStack.toJson(as.getChestplate()));
		o.set("Helmet", JsonItemStack.toJson(as.getHelmet()));
		o.set("MainHand", JsonItemStack.toJson(as.getMainHand()));
		o.set("OffHand", JsonItemStack.toJson(as.getOffHand()));
		o.set("Invulnerable", as.isInvulnerable());
		o.set("Glowing", as.isGlowing());
		o.set("Gravity", as.hasGravity());
		o.set("Collidable", as.isCollidable());
		o.setLocation("Loc", as.getLocation());
		return o.toJSON();
	}
	
	private static ArmorStandData getArmorStandDataFromJSON(JSONObject c) {
		ArmorStandData as = new ArmorStandData();
		Config o = new Config(c);
		as.setBoots(JsonItemStack.fromJson((String) o.get("Boots")));
		as.setLeggings(JsonItemStack.fromJson((String) o.get("Leggings")));
		as.setChestplate(JsonItemStack.fromJson((String) o.get("Chestplate")));
		as.setHelmet(JsonItemStack.fromJson((String) o.get("Helmet")));
		as.setMainHand(JsonItemStack.fromJson((String) o.get("MainHand")));
		as.setOffHand(JsonItemStack.fromJson((String) o.get("OffHand")));
		as.setInvulnerable((boolean) o.get("Invulnerable"));
		as.setGlowing((boolean) o.get("Glowing"));
		as.setGravity((boolean) o.get("Gravity"));
		as.setCollidable((boolean) o.get("Collidable"));
		as.setLocation(o.getLocation("Loc"));
		return as;
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity el = (LivingEntity) e.getEntity();
			if (morphsEntity.containsKey(el)) {
				e.setCancelled(true);
				el.setFireTicks(0);
			}
		}
	}
	
	@EventHandler
	private void onJoin(PlayerJoinEvent e) {
		for (FakePlayer fp : morphsFakePlayer.keySet()) {
			fp.spawn(e.getPlayer());
		}
	}

	public static void openMorphMenu(LivingEntity le, Player p) {
		register();
		p.closeInventory();
		p.openInventory(morphMenu);
		menusEntity.put(p, le);
	}
	
	public static void openMorphMenu(FakePlayer fp, Player p) {
		register();
		p.closeInventory();
		p.openInventory(morphMenu);
		menusFakePlayer.put(p, fp);
	}
	
	@EventHandler
	private void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		
		Inventory inv = e.getInventory();
		
		if (inv.equals(morphMenu)) {
			menusEntity.remove(p);
			menusFakePlayer.remove(p);
		}
	}
	
	@EventHandler
	public void Click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		Inventory inv = e.getInventory();
		ItemStack item = e.getCurrentItem();
		
		LivingEntity le = menusEntity.get(p);
		FakePlayer fp = menusFakePlayer.get(p);
		
		if (item != null) {
			if (inv.equals(morphMenu)) {
				if (le != null) {
					if (item.equals(remove)) {
						ArmorStand ar = le.getWorld().spawn(le.getLocation(), ArmorStand.class);
						if (morphsEntity.containsKey(le)) {
							ArmorStandData data = new ArmorStandData();
							copyAttributesToArmorStandData(data, le);
							data.apply(ar);
							ar.setArms(true);
						}
						ar.setVisible(true);
						le.remove();
						morphsEntity.remove(le);
						p.closeInventory();
						e.setCancelled(true);
					}
					if (item.equals(head)) {
						le.getEquipment().setHelmet(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(body)) {
						le.getEquipment().setChestplate(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(legins)) {
						le.getEquipment().setLeggings(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(boots)) {
						le.getEquipment().setBoots(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(lefthand)) {
						le.getEquipment().setItemInOffHand(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(righthand)) {
						le.getEquipment().setItemInMainHand(e.getCursor());
						e.setCancelled(true);
					}
					if (e.isCancelled()) {
						p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 100, (float) 1.7);
					}
				} else if (fp != null) {
					if (item.equals(remove)) {
						ArmorStand ar = p.getWorld().spawn(fp.getLocation(), ArmorStand.class);
						if (morphsFakePlayer.containsKey(fp)) {
							ArmorStandData data = new ArmorStandData();
							copyAttributesToArmorStandData(data, fp);
							data.apply(ar);
							ar.setArms(true);
						}
						ar.setVisible(true);
						fp.destroy();
						FakePlayer.all.remove(fp);
						morphsFakePlayer.remove(fp);
						p.closeInventory();
						e.setCancelled(true);
					}
					if (item.equals(head)) {
						fp.setHelmet(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(body)) {
						fp.setChestplate(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(legins)) {
						fp.setLeggings(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(boots)) {
						fp.setBoots(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(lefthand)) {
						fp.setItemInOffHand(e.getCursor());
						e.setCancelled(true);
					}
					if (item.equals(righthand)) {
						fp.setItemInMainHand(e.getCursor());
						e.setCancelled(true);
					}
					if (e.isCancelled()) {
						p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 100, (float) 1.7);
					}
				}
			}
		}
	}

	private static void register() {
		ItemMeta m = head.getItemMeta();
		m.setDisplayName("§6Chose Helmet");
		head.setItemMeta(m);
		
		m = body.getItemMeta();
		m.setDisplayName("§6Chose Chestplatte");
		body.setItemMeta(m);
		
		m = legins.getItemMeta();
		m.setDisplayName("§6Chose Leggings");
		legins.setItemMeta(m);
		
		m = boots.getItemMeta();
		m.setDisplayName("§6Chose Boots");
		boots.setItemMeta(m);
		
		m = remove.getItemMeta();
		m.setDisplayName("§cRemove");
		remove.setItemMeta(m);
		
		m = lefthand.getItemMeta();
		m.setDisplayName("§6Chose Left Hand");
		lefthand.setItemMeta(m);
		
		m = righthand.getItemMeta();
		m.setDisplayName("§6Chose Right Hand");
		righthand.setItemMeta(m);
		
		//________________________________
		
		morphMenu.setItem(Menu.getSlot(4, 5), remove);
		morphMenu.setItem(Menu.getSlot(0, 5), head);
		morphMenu.setItem(Menu.getSlot(1, 5), body);
		morphMenu.setItem(Menu.getSlot(1, 4), lefthand);
		morphMenu.setItem(Menu.getSlot(1, 6), righthand);
		morphMenu.setItem(Menu.getSlot(2, 5), legins);
		morphMenu.setItem(Menu.getSlot(3, 5), boots);
	}
	
	public static void copyAttributesToMorph(LivingEntity morph, ArmorStandData ar) {
		morph.getEquipment().setBoots(ar.getBoots());
		morph.getEquipment().setChestplate(ar.getChestplate());
		morph.setGlowing(ar.isGlowing());
		morph.setGravity(ar.hasGravity());
		morph.getEquipment().setHelmet(ar.getHelmet());
		morph.setInvulnerable(ar.isInvulnerable());
		morph.getEquipment().setItemInMainHand(ar.getMainHand());
		morph.getEquipment().setItemInOffHand(ar.getOffHand());
		morph.getEquipment().setLeggings(ar.getLeggings());
		morph.setCollidable(ar.isCollidable());
		morph.teleport(ar.getLocation());
	}
	
	private static void copyAttributesToArmorStandData(ArmorStandData ar, FakePlayer clip) {
		ar.setBoots(clip.getBoots());
		ar.setChestplate(clip.getChestplate());
		ar.setGlowing(clip.isGlowing());
		//ar.setGravity(clip.hasGravity());
		ar.setHelmet(clip.getHelmet());
		//ar.setInvulnerable(clip.isInvulnerable());
		ar.setMainHand(clip.getItemInMainHand());
		ar.setOffHand(clip.getItemInOffHand());
		ar.setLeggings(clip.getLeggings());
		//ar.setCollidable(clip.isCollidable());
		ar.setLocation(clip.getLocation());
	}
	
	public static void copyAttributesToArmorStandData(ArmorStandData ar, LivingEntity clip) {
		ar.setBoots(clip.getEquipment().getBoots());
		ar.setChestplate(clip.getEquipment().getChestplate());
		ar.setGlowing(clip.isGlowing());
		ar.setGravity(clip.hasGravity());
		ar.setHelmet(clip.getEquipment().getHelmet());
		ar.setInvulnerable(clip.isInvulnerable());
		ar.setMainHand(clip.getEquipment().getItemInMainHand());
		ar.setOffHand(clip.getEquipment().getItemInOffHand());
		ar.setLeggings(clip.getEquipment().getLeggings());
		ar.setCollidable(clip.isCollidable());
		ar.setLocation(clip.getLocation());
	}
}
