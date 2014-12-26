package me.dragongott.customgravity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable() {
		
		System.out.println("[CustomGravity] enabled v 1.0");
		System.out.println("[CustomGravity] by Dragongott");
		
		final FileConfiguration config = this.getConfig();
		
		
		config.addDefault("blocks", "98;42"); 
		
		config.options().copyDefaults(true);
		saveConfig();
		
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	public void onDisable() {
		
		System.out.println("[CustomGravity] disabled v 1.0");
		System.out.println("[CustomGravity] by Dragongott");
		
	}
	
	@EventHandler
	public void onPhysics(BlockPlaceEvent e) {
		
		Location loc = e.getBlock().getLocation();
		
		
		String ids[] = getConfig().getString("blocks").split(";");
		
		int i;

			for (i=0; i<ids.length; i++) { 
				
				
				ItemStack item = new ItemStack(e.getBlock().getType(), 1);
				int id = item.getTypeId();
				
				String ID = String.valueOf(id);
				
				
				if (ids[i].equalsIgnoreCase(ID))  {
					
					
					e.setCancelled(true);
					
					getServer().dispatchCommand(getServer().getConsoleSender(), "summon FallingSand " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + " {TileID:" + ID + ",Time:1}");
			
					
				}
				
			}
		
	}
	
	@EventHandler
	public void onDestroy(BlockBreakEvent e) {
		
		int y = e.getBlock().getY() + 1;
		Location loc = new Location(e.getBlock().getWorld(), e.getBlock().getX(), y, e.getBlock().getZ());
		
		
		String ids[] = getConfig().getString("blocks").split(";");
		
		int i;
	

			for (i=0; i<ids.length; i++) { 
				
				
				ItemStack item = new ItemStack(loc.getBlock().getType(), 1);
				int id = item.getTypeId();
				
				String ID = String.valueOf(id);
				
				
				if (ids[i].equalsIgnoreCase(ID))  {
					
					loc.getBlock().setType(Material.AIR);
					
					getServer().dispatchCommand(getServer().getConsoleSender(), "summon FallingSand " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + " {TileID:" + ID + ",Time:1}");
			
					
				}
				
			}
		
	}

}
