package de.paul.ArmorStandEdit.ArmorStandMenu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import de.paul.ArmorStandEdit.ArmorStandMenu.Morph.FakePlayer;

public class eyes {
	
	public static LivingEntity getTarget(Player p, int r) {
		List<Entity> nearbyE = p.getNearbyEntities(r, r, r);
		ArrayList<LivingEntity> livingE = new ArrayList<LivingEntity>();
		
		for (Entity e : nearbyE) {
			if (e instanceof LivingEntity) {
				livingE.add((LivingEntity) e);
			}
		}
		
		LivingEntity target = null;
		BlockIterator bItr = new BlockIterator(p, r);
		Block block;
		Location loc;
		int bx, by, bz;
		double ex, ey, ez;
		
		while (bItr.hasNext()) {
			block = bItr.next();
			bx = block.getX();
			by = block.getY();
			bz = block.getZ();
			for (LivingEntity e : livingE) {
				loc = e.getLocation();
				ex = loc.getX();
				ey = loc.getY();
				ez = loc.getZ();
				if ((bx-.75 <= ex && ex <= bx+1.75) && (bz-.75 <= ez && ez <= bz+1.75) && (by-1 <= ey && ey <= by+2.5)) {
					target = e;
				}
			}
		}
		return target;
	}
	
	public static FakePlayer getTarget(Player p, int r, ArrayList<FakePlayer> fps) {
		FakePlayer target = null;
		BlockIterator bItr = new BlockIterator(p, r);
		Block block;
		Location loc;
		int bx, by, bz;
		double ex, ey, ez;
		
		while (bItr.hasNext()) {
			block = bItr.next();
			bx = block.getX();
			by = block.getY();
			bz = block.getZ();
			for (FakePlayer fp : fps) {
				loc = fp.getLocation();
				ex = loc.getX();
				ey = loc.getY();
				ez = loc.getZ();
				if ((bx-.75 <= ex && ex <= bx+1.75) && (bz-.75 <= ez && ez <= bz+1.75) && (by-1 <= ey && ey <= by+2.5)) {
					target = fp;
				}
			}
		}
		return target;
	}
}
