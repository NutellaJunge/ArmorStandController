package pack;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;


public class main extends JavaPlugin {
	
	static public HashMap<Player, Menu> menus = new HashMap<>();
	
	@Override
	public void onEnable() {
		System.out.println("The Armor Stand Controlle Plugin by paul05 is startet.");
		Bukkit.getPluginManager().registerEvents(new Click(), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("The Armor Stand Controlle Plugin is stopt.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (command.getName().equalsIgnoreCase("editarmorstand")) {
				if (p.hasPermission("armorstand.edit")) {
					
					ArmorStand ar = null;
					
					ar = (ArmorStand) eyes.getTarget(p, 3, EntityType.ARMOR_STAND);
					
					if (ar != null) {
						Menu m = new Menu(ar, p);
						menus.put(p, m);
					} else {
						p.sendMessage("§cNo Armor Stand was found in your field of view.");
					}
					
				} else {
					p.sendMessage("§cI'm sorry, but you do not have the permissions to perform this command. Please contact the server administrators if you believe that this is in error.");
				}
			}
		} else {
			sender.sendMessage("The command can executet as player.");
		}
		
		return true;
	}
	
}
