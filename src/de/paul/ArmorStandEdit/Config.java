package de.paul.ArmorStandEdit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Config {
	
	private JSONObject c;
	private File f;

	public Config(File f) {
		c = readJSON(f);
	}
	
	public Config(JSONObject o) {
		c = o;
	}
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(c);
	}
	
	public String toJSONString() {
		return c.toJSONString();
	}
	
	public void set(String st, Object o) {
		c.put(st.toLowerCase(), o);
		if (getFile() != null) {
			save();
		}
	}
	
	public Config(String string) {
		try {
			JSONParser p = new JSONParser();
			c = (JSONObject) p.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public boolean contains(String s) {
		return c.containsKey(s);
	}
	
	public void save() {
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write(toString());
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return f;
	}
	
	public Object get(String st) {
		return c.get(st.toLowerCase());
	}

	private JSONObject readJSON(File f) {
		try {
			FileReader r = new FileReader(f);
			JSONParser p = new JSONParser();
			JSONObject o = (JSONObject) p.parse(r);
			this.f = f;
			r.close();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Location getLocation(String name) {
		JSONObject ob = (JSONObject) get(name);
		World world = Bukkit.getWorld((String) ob.get("world"));
		double x = (double) ob.get("x");
		double y = (double) ob.get("y");
		double z = (double) ob.get("z");
		double yaw = (double) ob.get("yaw");
		double pitch = (double) ob.get("pitch");
		return new Location(world, x, y, z, (float) yaw, (float) pitch);
	}
	
	public void setLocation(String name, Location loc) {
		Config c = new Config(new JSONObject());
		c.set("world", loc.getWorld().getName());
		c.set("x", loc.getX());
		c.set("y", loc.getY());
		c.set("z", loc.getZ());
		c.set("yaw", loc.getYaw());
		c.set("pitch", loc.getPitch());
		set(name, c.toJSON());
	}

	public JSONObject toJSON() {
		return c;
	}

}
