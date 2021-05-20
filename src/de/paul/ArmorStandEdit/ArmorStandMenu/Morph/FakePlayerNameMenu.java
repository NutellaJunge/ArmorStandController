package de.paul.ArmorStandEdit.ArmorStandMenu.Morph;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.paul.ArmorStandEdit.main;

public class FakePlayerNameMenu implements Listener {
	
	private static HashMap<Player, Inventory> invs = new HashMap<>();
	public static HashMap<Player, String> data = new HashMap<>();
	
	private ItemStack nameItem;
	
	public FakePlayerNameMenu(Player p) {
		Inventory anvil = Bukkit.createInventory(null, InventoryType.ANVIL, "§5Skin Player Name");
		
		nameItem =  new ItemStack(Material.PAPER);
		ItemMeta m = nameItem.getItemMeta();
		m.setDisplayName(p.getName());
		nameItem.setItemMeta(m);
		
		anvil.setItem(0, nameItem);
		
		invs.put(p, anvil);
		
		p.openInventory(anvil);
		
		Bukkit.getPluginManager().registerEvents(this, main.instance);
	}
	
	@EventHandler
	private void invClick(InventoryClickEvent e) {
		if (e.getClick() == ClickType.LEFT) {
			Player cp = (Player) e.getWhoClicked();
			
			if (invs.containsKey(cp)) {
				Inventory anvil = invs.get(cp);
				if (e.getClickedInventory() != null) {
					if (e.getClickedInventory().equals(anvil)) {
						e.setCancelled(true);
						ItemStack item = e.getCurrentItem();
						System.out.println(e.getSlot()+" _ "+item);
						if (item != null) {
							if (e.getSlot() == 0 || e.getSlot() == 2) {
								data.put(cp, item.getItemMeta().getDisplayName());
							}
						}
					}
				}
			}
		}
	}
}
