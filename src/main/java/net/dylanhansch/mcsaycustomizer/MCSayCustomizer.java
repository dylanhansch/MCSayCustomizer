package net.dylanhansch.mcsaycustomizer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MCSayCustomizer extends JavaPlugin {
	
	public static String NO_PERMISSION_MSG = ChatColor.DARK_RED + "You do not have permission for this command.";
	
	@Override
	public void onDisable(){
	}
	
	@Override
	public void onEnable(){
		saveDefaultConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		if (cmd.getName().equalsIgnoreCase("say")) {
			if (args.length == 0) {
				if (!sender.hasPermission("mcsaycustomizer.say")) {
					sender.sendMessage(NO_PERMISSION_MSG);
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Error: Not enough arguments!");
					return false;
				}
			}
			if (args.length >= 1) {
				if (!sender.hasPermission("mcsaycustomizer.say")) {
					sender.sendMessage(NO_PERMISSION_MSG);
					return true;
				} else {
					String sayFormat = getConfig().getString("say-format").replaceAll("&([0-9a-fl-oA-FL-O])", ChatColor.COLOR_CHAR + "$1");
					String message = "";
					for (String arg : args) {
						message = message + arg + " ";
					}
					message.replaceAll("&([0-9a-fl-oA-FL-O])", ChatColor.COLOR_CHAR + "$1");
					
					getServer().broadcastMessage(sayFormat.replace("%message", message));
					return true;
				}
			}
		}
		
		return false;
	}
}
