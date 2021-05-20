package de.paul.ArmorStandEdit;
import java.io.File;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import assets.assets;
import de.paul.ArmorStandEdit.ArmorStandMenu.Click;
import de.paul.ArmorStandEdit.ArmorStandMenu.Menu;
import de.paul.ArmorStandEdit.ArmorStandMenu.eyes;
import de.paul.ArmorStandEdit.ArmorStandMenu.Morph.FakePlayer;
import de.paul.ArmorStandEdit.ArmorStandMenu.Morph.Morph;


public class main extends JavaPlugin {
	
	static public HashMap<Player, Menu> menus = new HashMap<>();
	static public HashMap<Player, ArmorStand> clipbord = new HashMap<>();
	
	public static File langFolder;
	public static Config config;
	public static Language lang;

	static public Plugin instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		createConfig();
		
		Bukkit.getPluginManager().registerEvents(new Click(), instance);
		Bukkit.getPluginManager().registerEvents(new Morph(), instance);
		
		Morph.loadFromConfig(config);
	}
	
	private void createConfig() {
		langFolder = new File(getDataFolder().getAbsoluteFile(), "lang");
		if (!langFolder.exists()) {
			langFolder.mkdirs();
			assets.loadLanguages(langFolder);
		}
		
		File configFile = new File(getDataFolder().getAbsoluteFile(), "config.json");
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			assets.copyFile(configFile.getAbsolutePath(), "config.json");
		}
		
		File skinFile = new File(getDataFolder().getAbsoluteFile(), "playerSkins.json");
		if (!skinFile.exists()) {
			skinFile.getParentFile().mkdirs();
			assets.copyFile(skinFile.getAbsolutePath(), "skins.json");
		}
		
		SkinLoader.ini(new Config(skinFile));
		config = new Config(configFile);
		lang = new Language((String) config.get("lang"));
	}

	@Override
	public void onDisable() {
		Morph.saveToConfig(config);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (command.getName().equalsIgnoreCase("editarmorstand")) {
				if (command.testPermission(sender)) {
					LivingEntity le = eyes.getTarget(p, 3);
					FakePlayer fp = eyes.getTarget(p, 3, FakePlayer.all);
					
					if (fp != null) {
						if (Morph.morphsFakePlayer.containsKey(fp)) {
							Morph.openMorphMenu(fp, p);
							return true;
						}
					}
					if (le != null) {
						if (le.getType().equals(EntityType.ARMOR_STAND)) {
							ArmorStand ar = (ArmorStand) le;
							Menu m = new Menu(ar, p);
							menus.put(p, m);
						} else {
							if (!(le instanceof Player)) {
								if (Morph.morphsEntity.containsKey(le)) {
									Morph.openMorphMenu(le, p);
								}
							}
						}
						return true;
					} else {
						p.sendMessage("§cNo Armor Stand was found in your field of view.");
					}
				}
			}
		} else {
			sender.sendMessage("The command can only be executet as player.");
		}
		
		return false;
	}

	public static void copyAtributs(ArmorStand ar, ArmorStand clip, float yaw, float pitch) {
		ar.setArms(clip.hasArms());
		ar.setBasePlate(clip.hasBasePlate());
		ar.setBodyPose(clip.getBodyPose());
		ar.setBoots(clip.getBoots());
		ar.setChestplate(clip.getChestplate());
		ar.setGlowing(clip.isGlowing());
		ar.setGravity(clip.hasGravity());
		ar.setHeadPose(clip.getHeadPose());
		ar.setHelmet(clip.getHelmet());
		ar.setInvulnerable(clip.isInvulnerable());
		ar.getEquipment().setItemInMainHand(clip.getEquipment().getItemInMainHand());
		ar.getEquipment().setItemInOffHand(clip.getEquipment().getItemInOffHand());
		ar.setLeftArmPose(clip.getLeftArmPose());
		ar.setLeftLegPose(clip.getLeftLegPose());
		ar.setLeggings(clip.getLeggings());
		ar.setRightArmPose(clip.getRightArmPose());
		ar.setRightLegPose(clip.getRightLegPose());
		ar.setSmall(clip.isSmall());
		ar.setVisible(clip.isVisible());
		ar.setCollidable(clip.isCollidable());
		ar.teleport(new Location(ar.getWorld(), ar.getLocation().getX(), ar.getLocation().getY(), ar.getLocation().getZ(), yaw, pitch));
	}
	
}
