package de.paul.ArmorStandEdit.ArmorStandMenu.Morph;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.core.layout.PatternLayout;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import de.paul.ArmorStandEdit.SkinLoader;

public class FakePlayer {
	
	public static ArrayList<FakePlayer> all = new ArrayList<>();
	
	private Object ep;
	private boolean isMove = false;
	private boolean stop = false;
	private boolean live;
	private GameProfile gp;
	
	private Location location;
	private String skinName;
	
	public FakePlayer(String skinName, String name, Location loc) {
		gp = new GameProfile(UUID.randomUUID(), name);
		setSkin(skinName);
		
		try {
			Object craftServer = getClass(ReflectionPackageType.CraftBukkit, "CraftServer").cast(Bukkit.getServer());
			Object minecraftServer = craftServer.getClass().getMethod("getServer").invoke(craftServer);
			Object craftWorld = getClass(ReflectionPackageType.CraftBukkit, "CraftWorld").cast(loc.getWorld());
			Object worldServer = craftWorld.getClass().getMethod("getHandle").invoke(craftWorld);
			
			Constructor<?> entityPlayerConstructor = getClass(ReflectionPackageType.Server, "EntityPlayer").getConstructor(getClass(ReflectionPackageType.Server, "MinecraftServer"), getClass(ReflectionPackageType.Server, "WorldServer"), GameProfile.class, getClass(ReflectionPackageType.Server,  "PlayerInteractManager"));
			ep = entityPlayerConstructor.newInstance(minecraftServer, worldServer, gp, getClass(ReflectionPackageType.Server,  "PlayerInteractManager").getConstructor(getClass(ReflectionPackageType.Server, "WorldServer")).newInstance(worldServer));
			ep.getClass().getField("sentListPacket").set(ep, false);
			
			spawn();
			
			teleport(loc);
			
			all.add(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Location getLocation() {
		return location;
	}
	
	public int getEntityID() {
		try {
			return (int) ep.getClass().getMethod("getId").invoke(ep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void setSkin(String playername) {
		try {
			Property p = SkinLoader.getSkin(playername);
			gp.getProperties().put("textures", p);
			skinName = playername;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Class<?> entityPlayerClass = getClass(ReflectionPackageType.Server, "EntityPlayer");
	public static Class<?> entityPlayerArrayClass = Array.newInstance(entityPlayerClass, 1).getClass();
	public static Class<?> entityHumanClass = getClass(ReflectionPackageType.Server, "EntityHuman");
	public static Class<?> enumPlayerInfoActionClass = getClass(ReflectionPackageType.Server, "PacketPlayOutPlayerInfo$EnumPlayerInfoAction");
	
	private void spawn() {
		try {
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutPlayerInfo").getConstructor(enumPlayerInfoActionClass, entityPlayerArrayClass).newInstance(enumPlayerInfoActionClass.getField("ADD_PLAYER").get(null), toEntityArray(ep)));
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutNamedEntitySpawn").getConstructor(entityHumanClass).newInstance(entityHumanClass.cast(ep)));
			}
			live = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void spawn(Player p) {
		try {
			sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutPlayerInfo").getConstructor(enumPlayerInfoActionClass, entityPlayerArrayClass).newInstance(enumPlayerInfoActionClass.getField("ADD_PLAYER").get(null), toEntityArray(ep)));
			sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutNamedEntitySpawn").getConstructor(entityHumanClass).newInstance(entityHumanClass.cast(ep)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Object toEntityArray(Object entity) {
		try {
			Object array = Array.newInstance(entityPlayerClass, 1);
			Array.set(array, 0, entity);
			return array;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void teleport(Location loc) {
		try {
			ep.getClass().getMethod("setLocation", double.class, double.class, double.class, float.class, float.class).invoke(ep, loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
			location = loc;
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityTeleport").getConstructor(getClass(ReflectionPackageType.Server, "Entity")).newInstance(getClass(ReflectionPackageType.Server, "Entity").cast(ep)));
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityHeadRotation").getConstructor(getClass(ReflectionPackageType.Server, "Entity"), byte.class).newInstance(getClass(ReflectionPackageType.Server, "Entity").cast(ep), getFixRotation(loc.getYaw())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isStop() {
		return stop;
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public boolean isMove() {
		return isMove;
	}
	
	public boolean isLiving() {
		return live;
	}
	
	public String getSkinName() {
		return skinName;
	}
	
	public void destroy() {
		try {
			live = false;
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityDestroy").getConstructor(int[].class).newInstance(new int[] {getEntityID()}));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendPacket(Player p, Object packet) {
		try {
			Object handle = p.getClass().getMethod("getHandle").invoke(p);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getClass(ReflectionPackageType.Server, "Packet")).invoke(playerConnection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Class<?> getClass(ReflectionPackageType t, String className) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName(t.pack+version+"."+className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private byte getFixRotation(float yawpitch){
        return (byte) ((int) (yawpitch * 256.0F / 360.0F));
	}
	
	private ItemStack ItemInMainHand;
	private ItemStack ItemInOffHand;
	private ItemStack Helmet;
	private ItemStack Chestplate;
	private ItemStack Leggings;
	private ItemStack Boots;
	private boolean Glow;
	
	public static Class<?> itemStackClass = getClass(ReflectionPackageType.Server, "ItemStack");
	public static Class<?> craftItemStackClass = getClass(ReflectionPackageType.CraftBukkit, "inventory.CraftItemStack");
	public static Class<?> enumItemSlotClass = getClass(ReflectionPackageType.Server, "EnumItemSlot");
	
	public void setItemInMainHand(ItemStack item) {
		ItemInMainHand = item;
		try {
			Object craftItem = itemStackClass.cast(craftItemStackClass.getMethod("asNMSCopy", ItemStack.class).invoke(null, item));
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityEquipment").getConstructor(int.class, enumItemSlotClass, itemStackClass).newInstance(getEntityID(), enumItemSlotClass.getField("MAINHAND").get(null), craftItem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setItemInOffHand(ItemStack item) {
		ItemInOffHand = item;
		try {
			Object craftItem = itemStackClass.cast(craftItemStackClass.getMethod("asNMSCopy", ItemStack.class).invoke(null, item));
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityEquipment").getConstructor(int.class, enumItemSlotClass, itemStackClass).newInstance(getEntityID(), enumItemSlotClass.getField("OFFHAND").get(null), craftItem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setHelmet(ItemStack item) {
		Helmet = item;
		try {
			Object craftItem = itemStackClass.cast(craftItemStackClass.getMethod("asNMSCopy", ItemStack.class).invoke(null, item));
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityEquipment").getConstructor(int.class, enumItemSlotClass, itemStackClass).newInstance(getEntityID(), enumItemSlotClass.getField("HEAD").get(null), craftItem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setChestplate(ItemStack item) {
		Chestplate = item;
		try {
			Object craftItem = itemStackClass.cast(craftItemStackClass.getMethod("asNMSCopy", ItemStack.class).invoke(null, item));
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityEquipment").getConstructor(int.class, enumItemSlotClass, itemStackClass).newInstance(getEntityID(), enumItemSlotClass.getField("CHEST").get(null), craftItem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setLeggings(ItemStack item) {
		Leggings = item;
		try {
			Object craftItem = itemStackClass.cast(craftItemStackClass.getMethod("asNMSCopy", ItemStack.class).invoke(null, item));
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityEquipment").getConstructor(int.class, enumItemSlotClass, itemStackClass).newInstance(getEntityID(), enumItemSlotClass.getField("LEGS").get(null), craftItem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setBoots(ItemStack item) {
		Boots = item;
		try {
			Object craftItem = itemStackClass.cast(craftItemStackClass.getMethod("asNMSCopy", ItemStack.class).invoke(null, item));
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityEquipment").getConstructor(int.class, enumItemSlotClass, itemStackClass).newInstance(getEntityID(), enumItemSlotClass.getField("FEET").get(null), craftItem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setGlow(boolean glow) {
		Glow = glow;
		try {
			if (Glow) {
				Object effect = getClass(ReflectionPackageType.Server, "MobEffect").getConstructor(getClass(ReflectionPackageType.Server, "MobEffectList"), int.class, int.class).newInstance(getClass(ReflectionPackageType.Server, "MobEffectList").getMethod("fromId", int.class).invoke(null, PotionEffectType.GLOWING.getId()), 0, 99999);
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutEntityEffect").getConstructor(int.class, getClass(ReflectionPackageType.Server, "MobEffect")).newInstance(getEntityID(), effect));
				}
			} else {
				for (Player p : Bukkit.getOnlinePlayers()) {
					sendPacket(p, getClass(ReflectionPackageType.Server, "PacketPlayOutRemoveEntityEffect").getConstructor(int.class, getClass(ReflectionPackageType.Server, "MobEffectList")).newInstance(getEntityID(), getClass(ReflectionPackageType.Server, "MobEffectList").getMethod("fromId", int.class).invoke(null, PotionEffectType.GLOWING.getId())));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ItemStack getItemInMainHand() {
		return ItemInMainHand;
	}
	
	public ItemStack getItemInOffHand() {
		return ItemInOffHand;
	}
	
	public ItemStack getHelmet() {
		return Helmet;
	}
	
	public ItemStack getChestplate() {
		return Chestplate;
	}
	
	public ItemStack getLeggings() {
		return Leggings;
	}
	
	public ItemStack getBoots() {
		return Boots;
	}
	
	public boolean isGlowing() {
		return Glow;
	}

	public void setAttributes(ArmorStandData ar) {
		setHelmet(ar.getHelmet());
		setChestplate(ar.getChestplate());
		setLeggings(ar.getLeggings());
		setBoots(ar.getBoots());
		setItemInMainHand(ar.getMainHand());
		setItemInOffHand(ar.getOffHand());
		
		setGlow(ar.isGlowing());
	}
}
