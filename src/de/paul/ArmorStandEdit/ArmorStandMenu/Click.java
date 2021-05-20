package de.paul.ArmorStandEdit.ArmorStandMenu;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import de.paul.ArmorStandEdit.main;
import de.paul.ArmorStandEdit.ArmorStandMenu.Morph.Morph;

public class Click implements Listener {
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack item = e.getCurrentItem();
		if (item != null) {
			if (item.equals(Menu.back)) {
				main.menus.get(p).openMainMenu(p);
				e.setCancelled(true);
			}
			if (item.equals(Menu.movex)) {
				e.setCancelled(true);
			}
			if (item.equals(Menu.movey)) {
				e.setCancelled(true);
			}
			if (item.equals(Menu.movez)) {
				e.setCancelled(true);
			}
			if (item.equals(Menu.moverot)) {
				e.setCancelled(true);
			}
			if (inv.equals(Menu.MorphMenu)) {
				if (item.equals(Menu.morphzomb)) {
					Morph.morph(p, main.menus.get(p).getArmorStand(), EntityType.ZOMBIE);
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 50, (float) 2.6);
					e.setCancelled(true);
				}
				if (item.equals(Menu.morphskel)) {
					Morph.morph(p, main.menus.get(p).getArmorStand(), EntityType.SKELETON);
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 50, (float) 2.6);
					e.setCancelled(true);
				}
				if (item.equals(Menu.morphhusk)) {
					Morph.morph(p, main.menus.get(p).getArmorStand(), EntityType.HUSK);
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 50, (float) 2.6);
					e.setCancelled(true);
				}
				if (item.equals(Menu.morphwskel)) {
					Morph.morph(p, main.menus.get(p).getArmorStand(), EntityType.WITHER_SKELETON);
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 50, (float) 2.6);
					e.setCancelled(true);
				}
				if (item.equals(Menu.morphstray)) {
					Morph.morph(p, main.menus.get(p).getArmorStand(), EntityType.STRAY);
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 50, (float) 2.6);
					e.setCancelled(true);
				}
				if (item.equals(Menu.morphplayer)) {
					Morph.morph(p, main.menus.get(p).getArmorStand(), EntityType.PLAYER);
					p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 50, (float) 2.6);
					e.setCancelled(true);
				}
			}
			if (inv.equals(Menu.MainMenu)) {
				if (item.equals(Menu.move)) {
					main.menus.get(p).openMoveMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.morph)) {
					main.menus.get(p).openMorphMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.tags)) {
					main.menus.get(p).openTagMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.head)) {
					main.menus.get(p).openMoveHeadMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.leftarm)) {
					main.menus.get(p).openMoveLeftArmMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.rightarm)) {
					main.menus.get(p).openMoveRightArmMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.lefthand)) {
					main.menus.get(p).getArmorStand().getEquipment().setItemInOffHand(e.getCursor());
					e.setCancelled(true);
				}
				if (item.equals(Menu.righthand)) {
					main.menus.get(p).getArmorStand().getEquipment().setItemInMainHand(e.getCursor());
					e.setCancelled(true);
				}
				if (item.equals(Menu.body)) {
					main.menus.get(p).openMoveBodyMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.leftleg)) {
					main.menus.get(p).openMoveLeftLegMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.rightleg)) {
					main.menus.get(p).openMoveRightLegMenu(p);
					e.setCancelled(true);
				}
				if (item.equals(Menu.remove)) {
					main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, -1000, 0));
					p.closeInventory();
					e.setCancelled(true);
				}
				if (item.equals(Menu.copy)) {
					main.clipbord.put(p, main.menus.get(p).getArmorStand());
					p.closeInventory();
					e.setCancelled(true);
				}
				if (item.equals(Menu.paste)) {
					ArmorStand clipbord = main.clipbord.get(p);
					if (clipbord != null) {
						Location loc = main.menus.get(p).getArmorStand().getLocation();
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, -1000, 0));
						ArmorStand ar = p.getWorld().spawn(loc, clipbord.getClass());
						main.copyAtributs(ar, clipbord, clipbord.getLocation().getYaw(), clipbord.getLocation().getPitch());
					} else {
						p.sendMessage("§5Your clipbord is empty.");
					}
					p.closeInventory();
					e.setCancelled(true);
				}
				if (item.equals(Menu.reset)) {
					Location loc = main.menus.get(p).getArmorStand().getLocation();
					main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, -1000, 0));
					loc.setYaw(0);
					loc.setPitch(0);
					p.getWorld().spawn(loc, ArmorStand.class);
					p.closeInventory();
					e.setCancelled(true);
				}
			}
			if (inv.equals(Menu.MoveMenu)) {
				if (item.equals(Menu.movexp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0.01, 0, 0));
						e.setCancelled(true);
					} else {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movexn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(-0.01, 0, 0));
						e.setCancelled(true);
					} else {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(-0.1, 0, 0));
						e.setCancelled(true);	
					}
				}
				if (item.equals(Menu.moveyp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, 0.01, 0));
						e.setCancelled(true);
					} else {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, 0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, -0.01, 0));
						e.setCancelled(true);
					} else {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, -0.1, 0));
						e.setCancelled(true);	
					}
				}
				if (item.equals(Menu.movezp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, 0, 0.01));
						e.setCancelled(true);
					} else {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, 0, 0.1));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, 0, -0.01));
						e.setCancelled(true);
					} else {
						main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, 0, -0.1));
						e.setCancelled(true);	
					}
				}
				if (item.equals(Menu.moverotright)) {
					ArmorStand ar = main.menus.get(p).getArmorStand();
					ar.teleport(new Location(ar.getWorld(), ar.getLocation().getX(),  ar.getLocation().getY(),  ar.getLocation().getZ(),  ar.getLocation().getYaw() - 4,  ar.getLocation().getPitch()));
					e.setCancelled(true);
				}
				if (item.equals(Menu.moverotleft)) {
					ArmorStand ar = main.menus.get(p).getArmorStand();
					ar.teleport(new Location(ar.getWorld(), ar.getLocation().getX(),  ar.getLocation().getY(),  ar.getLocation().getZ(),  ar.getLocation().getYaw() + 4,  ar.getLocation().getPitch()));
					e.setCancelled(true);
				}
			}
			if (inv.equals(Menu.TagMenu)) {
				if (item.equals(Menu.taggravity)) {
					main.menus.get(p).getArmorStand().setGravity(!main.menus.get(p).getArmorStand().hasGravity());
					p.sendMessage("§dThe Gravity is Change to " + main.menus.get(p).getArmorStand().hasGravity());
					e.setCancelled(true);
				}
				if (item.equals(Menu.tagarms)) {
					main.menus.get(p).getArmorStand().setArms(!main.menus.get(p).getArmorStand().hasArms());
					p.sendMessage("§bThe Arms are Changed to " + main.menus.get(p).getArmorStand().hasArms());
					e.setCancelled(true);
				}
				if (item.equals(Menu.taginvis)) {
					main.menus.get(p).getArmorStand().setVisible(!main.menus.get(p).getArmorStand().isVisible());
					p.sendMessage("§aThe Armor Stand is Visible: " + main.menus.get(p).getArmorStand().isVisible());
					e.setCancelled(true);
				}
				if (item.equals(Menu.tagsmall)) {
					main.menus.get(p).getArmorStand().setSmall(!main.menus.get(p).getArmorStand().isSmall());
					p.sendMessage("§2The Armor Stand is Small: " + main.menus.get(p).getArmorStand().isSmall());
					e.setCancelled(true);
				}
				if (item.equals(Menu.tagbase)) {
					main.menus.get(p).getArmorStand().setBasePlate(!main.menus.get(p).getArmorStand().hasBasePlate());
					p.sendMessage("§5The Base Plate is change to " + main.menus.get(p).getArmorStand().hasBasePlate());
					e.setCancelled(true);
				}
				if (item.equals(Menu.taghead)) {
					main.menus.get(p).getArmorStand().setHelmet(e.getCursor());
					p.sendMessage("§3The Head is change to " + main.menus.get(p).getArmorStand().getHelmet().getType().name().toUpperCase());
					e.setCancelled(true);
				}
				if (item.equals(Menu.tagglow)) {
					main.menus.get(p).getArmorStand().setGlowing(!main.menus.get(p).getArmorStand().isGlowing());
					p.sendMessage("§aThe Glowing Effekt is change to " + main.menus.get(p).getArmorStand().isGlowing());
					e.setCancelled(true);
				}
				if (item.equals(Menu.taginvu)) {
					main.menus.get(p).getArmorStand().setInvulnerable(!main.menus.get(p).getArmorStand().isInvulnerable());
					p.sendMessage("§5The Indestructibility is change to " + main.menus.get(p).getArmorStand().isInvulnerable());
					e.setCancelled(true);
				}
				if (item.equals(Menu.tagdis)) {
					main.menus.get(p).getArmorStand().setCollidable(!main.menus.get(p).getArmorStand().isCollidable());
					p.sendMessage("§4The locked Slots ar changed to " + !main.menus.get(p).getArmorStand().isCollidable());
					e.setCancelled(true);
				}
			}
			EulerAngle eu;
			if (inv.equals(Menu.MoveHeadMenu)) {
				if (item.equals(Menu.movexp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movexn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(-0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(-0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, -0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, -0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0, 0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0, 0.1));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0, -0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getHeadPose();
						main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0, -0.1));
						e.setCancelled(true);
					}
				}
			}
			if (inv.equals(Menu.MoveLeftArmMenu)) {
				if (item.equals(Menu.movexp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movexn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(-0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(-0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, -0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, -0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0, 0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0, 0.1));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0, -0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftArmPose();
						main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0, -0.1));
						e.setCancelled(true);
					}
				}
			}
			if (inv.equals(Menu.MoveRightArmMenu)) {
				if (item.equals(Menu.movexp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movexn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(-0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(-0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, -0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, -0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0, 0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0, 0.1));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0, -0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightArmPose();
						main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0, -0.1));
						e.setCancelled(true);
					}
				}
			}
			if (inv.equals(Menu.MoveBodyMenu)) {
				if (item.equals(Menu.movexp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movexn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(-0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(-0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, -0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, -0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0, 0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0, 0.1));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0, -0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getBodyPose();
						main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0, -0.1));
						e.setCancelled(true);
					}
				}
			}
			if (inv.equals(Menu.MoveLeftLegMenu)) {
				if (item.equals(Menu.movexp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movexn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(-0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(-0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, -0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, -0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0, 0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0, 0.1));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0, -0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getLeftLegPose();
						main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0, -0.1));
						e.setCancelled(true);
					}
				}
			}
			if (inv.equals(Menu.MoveRightLegMenu)) {
				if (item.equals(Menu.movexp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movexn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(-0.01, 0, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(-0.1, 0, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.moveyn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, -0.01, 0));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, -0.1, 0));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezp)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0, 0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0, 0.1));
						e.setCancelled(true);
					}
				}
				if (item.equals(Menu.movezn)) {
					if (e.getClick() != ClickType.SHIFT_LEFT) {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0, -0.01));
						e.setCancelled(true);
					} else {
						eu = main.menus.get(p).getArmorStand().getRightLegPose();
						main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0, -0.1));
						e.setCancelled(true);
					}
				}
			}
			if (e.isCancelled()) {
				p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 100, (float) 1.5);
			}
		}
	}
	
	@EventHandler
	public void onInterectArmorStand(PlayerArmorStandManipulateEvent e) {
		ArmorStand ar = e.getRightClicked();
		if (!ar.isCollidable()) {
			e.setCancelled(true);
		}
	}
}
