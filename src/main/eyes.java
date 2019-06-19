package pack;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class eyes {
	
	 static public LivingEntity getTarget(Player p, int r, EntityType et) {
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
	        // loop through player's line of sight
	        while (bItr.hasNext()) {
	                block = bItr.next();
	                bx = block.getX();
	                by = block.getY();
	                bz = block.getZ();
	                        // check for entities near this block in the line of sight
	                        for (LivingEntity e : livingE) {
	                                loc = e.getLocation();
	                                ex = loc.getX();
	                                ey = loc.getY();
	                                ez = loc.getZ();
	                                if ((bx-.75 <= ex && ex <= bx+1.75) && (bz-.75 <= ez && ez <= bz+1.75) && (by-1 <= ey && ey <= by+2.5)) {
	                                        if (et == null) {
		                                        target = e;
		                                        break;
	                                        } else {
												if (e.getType() == et) {
													target = e;
													break;
												}
											}
	                                }
	                        }
	                }
	        return target;

	 }
	
}
