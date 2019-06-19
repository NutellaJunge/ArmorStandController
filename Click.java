package pack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class Click implements Listener {
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack item = e.getCurrentItem();
		if (inv.equals(Menu.MainMenu)) {
			if (item.equals(Menu.move)) {
				main.menus.get(p).openMoveMenu(p);
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
			if (item.equals(Menu.reset)) {
				Location loc = main.menus.get(p).getArmorStand().getLocation(); 
				main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, -1000, 0));
				p.getWorld().spawn(loc, ArmorStand.class);
				p.closeInventory();
				e.setCancelled(true);
			}
		}
		if (inv.equals(Menu.MoveMenu)) {
			if (item.equals(Menu.moveup)) {
				if (e.getClick() != ClickType.SHIFT_LEFT) {
					main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, 0.01, 0));
					e.setCancelled(true);
				} else {
					main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, 1, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.movedown)) {
				if (e.getClick() != ClickType.SHIFT_LEFT) {
					main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, -0.01, 0));
					e.setCancelled(true);
				} else {
					main.menus.get(p).getArmorStand().teleport(main.menus.get(p).getArmorStand().getLocation().add(0, -1, 0));
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
				p.closeInventory();
				e.setCancelled(true);
			}
			if (item.equals(Menu.tagarms)) {
				main.menus.get(p).getArmorStand().setArms(!main.menus.get(p).getArmorStand().hasArms());
				p.sendMessage("§bThe Arms are Changed to " + main.menus.get(p).getArmorStand().hasArms());
				p.closeInventory();
				e.setCancelled(true);
			}
			if (item.equals(Menu.taginvis)) {
				main.menus.get(p).getArmorStand().setVisible(!main.menus.get(p).getArmorStand().isVisible());
				p.sendMessage("§aThe Armor Stand is Visible: " + main.menus.get(p).getArmorStand().isVisible());
				p.closeInventory();
				e.setCancelled(true);
			}
			if (item.equals(Menu.tagsmall)) {
				main.menus.get(p).getArmorStand().setSmall(!main.menus.get(p).getArmorStand().isSmall());
				p.sendMessage("§2The Armor Stand is Small: " + main.menus.get(p).getArmorStand().isSmall());
				p.closeInventory();
				e.setCancelled(true);
			}
			if (item.equals(Menu.tagbase)) {
				main.menus.get(p).getArmorStand().setBasePlate(!main.menus.get(p).getArmorStand().hasBasePlate());
				p.sendMessage("§5The Base Plate is change to " + main.menus.get(p).getArmorStand().hasBasePlate());
				p.closeInventory();
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
				p.closeInventory();
				e.setCancelled(true);
			}
			if (item.equals(Menu.taginvu)) {
				main.menus.get(p).getArmorStand().setInvulnerable(!main.menus.get(p).getArmorStand().isInvulnerable());
				p.sendMessage("§5The Indestructibility is change to " + main.menus.get(p).getArmorStand().isInvulnerable());
				p.closeInventory();
				e.setCancelled(true);
			}
		}
		EulerAngle eu;
		if (inv.equals(Menu.MoveHeadMenu)) {
			if (item.equals(Menu.moveback)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getHeadPose();
					main.menus.get(p).getArmorStand().setHeadPose(eu.add(-0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getHeadPose();
					main.menus.get(p).getArmorStand().setHeadPose(eu.add(-0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.movevor)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getHeadPose();
					main.menus.get(p).getArmorStand().setHeadPose(eu.add(0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getHeadPose();
					main.menus.get(p).getArmorStand().setHeadPose(eu.add(0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveleft)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getHeadPose();
					main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0, 0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getHeadPose();
					main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0, 0.01));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveright)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getHeadPose();
					main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0, -0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getHeadPose();
					main.menus.get(p).getArmorStand().setHeadPose(eu.add(0, 0, -0.01));
					e.setCancelled(true);
				}
			}
		}
		if (inv.equals(Menu.MoveBodyMenu)) {
			if (item.equals(Menu.moveback)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getBodyPose();
					main.menus.get(p).getArmorStand().setBodyPose(eu.add(0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getBodyPose();
					main.menus.get(p).getArmorStand().setBodyPose(eu.add(0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.movevor)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getBodyPose();
					main.menus.get(p).getArmorStand().setBodyPose(eu.add(-0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getBodyPose();
					main.menus.get(p).getArmorStand().setBodyPose(eu.add(-0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveleft)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getBodyPose();
					main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0, 0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getBodyPose();
					main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0, 0.01));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveright)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getBodyPose();
					main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0, -0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getBodyPose();
					main.menus.get(p).getArmorStand().setBodyPose(eu.add(0, 0, -0.01));
					e.setCancelled(true);
				}
			}
		}
		if (inv.equals(Menu.MoveLeftArmMenu)) {
			if (item.equals(Menu.moveback)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getLeftArmPose();
					main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getLeftArmPose();
					main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.movevor)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getLeftArmPose();
					main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(-0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getLeftArmPose();
					main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(-0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveleft)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getLeftArmPose();
					main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0, 0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getLeftArmPose();
					main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0, 0.01));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveright)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getLeftArmPose();
					main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0, -0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getLeftArmPose();
					main.menus.get(p).getArmorStand().setLeftArmPose(eu.add(0, 0, -0.01));
					e.setCancelled(true);
				}
			}
		}
		if (inv.equals(Menu.MoveRightArmMenu)) {
			if (item.equals(Menu.moveback)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getRightArmPose();
					main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getRightArmPose();
					main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.movevor)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getRightArmPose();
					main.menus.get(p).getArmorStand().setRightArmPose(eu.add(-0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getRightArmPose();
					main.menus.get(p).getArmorStand().setRightArmPose(eu.add(-0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveleft)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getRightArmPose();
					main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0, 0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getRightArmPose();
					main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0, 0.01));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveright)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getRightArmPose();
					main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0, -0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getRightArmPose();
					main.menus.get(p).getArmorStand().setRightArmPose(eu.add(0, 0, -0.01));
					e.setCancelled(true);
				}
			}
		}
		if (inv.equals(Menu.MoveLeftLegMenu)) {
			if (item.equals(Menu.moveback)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getLeftLegPose();
					main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getLeftLegPose();
					main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.movevor)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getLeftLegPose();
					main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(-0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getLeftLegPose();
					main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(-0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveleft)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getLeftLegPose();
					main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0, 0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getLeftLegPose();
					main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0, 0.01));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveright)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getLeftLegPose();
					main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0, -0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getLeftLegPose();
					main.menus.get(p).getArmorStand().setLeftLegPose(eu.add(0, 0, -0.01));
					e.setCancelled(true);
				}
			}
		}
		if (inv.equals(Menu.MoveRightLegMenu)) {
			if (item.equals(Menu.moveback)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getRightLegPose();
					main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getRightLegPose();
					main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.movevor)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getRightLegPose();
					main.menus.get(p).getArmorStand().setRightLegPose(eu.add(-0.1, 0, 0));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getRightLegPose();
					main.menus.get(p).getArmorStand().setRightLegPose(eu.add(-0.01, 0, 0));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveleft)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getRightLegPose();
					main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0, 0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getRightLegPose();
					main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0, 0.01));
					e.setCancelled(true);
				}
			}
			if (item.equals(Menu.moveright)) {
				if (e.getClick() == ClickType.SHIFT_LEFT) {
					eu = main.menus.get(p).getArmorStand().getRightLegPose();
					main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0, -0.1));
					e.setCancelled(true);
				} else {
					eu = main.menus.get(p).getArmorStand().getRightLegPose();
					main.menus.get(p).getArmorStand().setRightLegPose(eu.add(0, 0, -0.01));
					e.setCancelled(true);
				}
			}
		}
	}
}
