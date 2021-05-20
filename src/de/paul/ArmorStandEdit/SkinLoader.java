package de.paul.ArmorStandEdit;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mojang.authlib.properties.Property;

public class SkinLoader {
	
	static private Config c;
	static private HashMap<String, Property> skins = new HashMap<>();
	
	public static void ini(Config _c) {
		c = _c;
		JSONArray array = (JSONArray) c.get("data");
		if (array == null) {
			array = new JSONArray();
		}
		for (Object object : array) {
			JSONObject o = (JSONObject) object;
			String uuid = (String) o.get("uuid");
			String value = (String) o.get("value");
			String signature = (String) o.get("signatur");
			Property p = new Property("textures", value, signature);
			skins.put(uuid, p);
		}
		save();
	}
	
	private static void save() {
		JSONArray array = new JSONArray();
		
		for (Entry<String, Property> e : skins.entrySet()) {
			JSONObject o = new JSONObject();
			o.put("uuid", e.getKey());
			Property p = e.getValue();
			o.put("value", p.getValue());
			o.put("signatur", p.getSignature());
			array.add(o);
		}
		
		c.set("data", array);
		c.save();
	}
	
	public static Config getConfig() {
		return c;
	}
	
	public static HashMap<String, Property> getSkins() {
		return skins;
	}

	public static Property getSkin(String playername) throws Exception {
		if (Bukkit.getOfflinePlayer(playername) != null) {
			if (!skins.containsKey(Bukkit.getOfflinePlayer(playername).getUniqueId().toString())) {
				try {
					URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/"+Bukkit.getOfflinePlayer(playername).getUniqueId().toString().replace("-", "")+"?unsigned=false");
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					con.connect();
					System.out.println(con.getResponseCode());
					if (con.getResponseCode() == 200) {
						JSONObject o = (JSONObject) new JSONParser().parse(new InputStreamReader(con.getInputStream()));
						JSONObject t = (JSONObject) ((JSONArray)o.get("properties")).get(0);
						String value = (String) t.get("value");
						String signature = (String) t.get("signature");
						skins.put(Bukkit.getOfflinePlayer(playername).getUniqueId().toString(), new Property("textures", value, signature));
						save();
					} else {
						throw new Exception("Cant find Player Skin Data");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return skins.get(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
		} else {
			throw new Exception("Cant find Player");
		}
	}

}
