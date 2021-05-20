package de.paul.ArmorStandEdit;

import java.io.File;
import java.io.FileReader;
import org.bukkit.ChatColor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Language {
	
	private JSONObject f;
	private File langFile;

	public Language(String lang) {
		try {
			langFile = new File(main.langFolder.getAbsolutePath()+"/"+lang+".lang");
			f = (JSONObject) new JSONParser().parse(new FileReader(langFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getLangString(String key) {
		return ChatColor.translateAlternateColorCodes('&', (String) f.get(key));
	}

	public String getLangFileName() {
		return langFile.getName();
	}

}
