package com.loohp.interactivechatdiscordsrvaddon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.loohp.interactivechat.InteractiveChat;
import com.loohp.interactivechat.Utils.ChatColorUtils;
import com.loohp.interactivechatdiscordsrvaddon.Updater.Updater;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor, TabCompleter {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!label.equalsIgnoreCase("interactivechatdiscordsrv") && !label.equalsIgnoreCase("icd")) {
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.AQUA + "InteractiveChat DiscordSRV Addon written by LOOHP!");
			sender.sendMessage(ChatColor.GOLD + "You are running ICDiscordSRVAddon version: " + InteractiveChatDiscordSrvAddon.plugin.getDescription().getVersion());
			return true;
		}
		
		if (args[0].equalsIgnoreCase("reloadconfig")) {
			if (sender.hasPermission("interactivechatdiscordsrv.reloadconfig")) {
				InteractiveChatDiscordSrvAddon.plugin.reloadConfig();
				InteractiveChatDiscordSrvAddon.plugin.loadConfig();
				sender.sendMessage(InteractiveChatDiscordSrvAddon.plugin.reloadConfigMessage);
			} else {
				sender.sendMessage(InteractiveChat.NoPermission);
			}
			return true;
		}
		
		if (args[0].equalsIgnoreCase("reloadtexture")) {
			if (sender.hasPermission("interactivechatdiscordsrv.reloadtexture")) {
				InteractiveChatDiscordSrvAddon.plugin.reloadTextures();
				sender.sendMessage(InteractiveChatDiscordSrvAddon.plugin.reloadTextureMessage);
			} else {
				sender.sendMessage(InteractiveChat.NoPermission);
			}
			return true;
		}
		
		if (args[0].equalsIgnoreCase("update")) {
			if (sender.hasPermission("interactivechatdiscordsrv.update")) {
				sender.sendMessage(ChatColor.AQUA + "[ICDiscordSRVAddon] InteractiveChat DiscordSRV Addon written by LOOHP!");
				sender.sendMessage(ChatColor.GOLD + "[ICDiscordSRVAddon] You are running ICDiscordSRVAddon version: " + InteractiveChatDiscordSrvAddon.plugin.getDescription().getVersion());
				Bukkit.getScheduler().runTaskAsynchronously(InteractiveChatDiscordSrvAddon.plugin, () -> {
					String version = Updater.checkUpdate();
					if (version.equals("latest")) {
						sender.sendMessage(ChatColor.GREEN + "[ICDiscordSRVAddon] You are running the latest version!");
					} else {
						Updater.sendUpdateMessage(sender, version);
					}
				});
			} else {
				sender.sendMessage(InteractiveChat.NoPermission);
			}
			return true;
		}
		
		sender.sendMessage(ChatColorUtils.translateAlternateColorCodes('&', Bukkit.spigot().getConfig().getString("messages.unknown-command")));
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> tab = new ArrayList<String>();
		if (!label.equalsIgnoreCase("interactivechatdiscordsrv") && !label.equalsIgnoreCase("icd")) {
			return tab;
		}
		
		switch (args.length) {
		case 0:
			if (sender.hasPermission("interactivechatdiscordsrv.reloadconfig")) {
				tab.add("reloadconfig");
			}
			if (sender.hasPermission("interactivechatdiscordsrv.reloadtexture")) {
				tab.add("reloadtexture");
			}
			if (sender.hasPermission("interactivechatdiscordsrv.update")) {
				tab.add("update");
			}
			return tab;
		case 1:
			if (sender.hasPermission("interactivechatdiscordsrv.reloadconfig")) {
				if ("reloadconfig".startsWith(args[0].toLowerCase())) {
					tab.add("reloadconfig");
				}
			}
			if (sender.hasPermission("interactivechatdiscordsrv.reloadtexture")) {
				if ("reloadtexture".startsWith(args[0].toLowerCase())) {
					tab.add("reloadtexture");
				}
			}
			if (sender.hasPermission("interactivechatdiscordsrv.update")) {
				if ("update".startsWith(args[0].toLowerCase())) {
					tab.add("update");
				}
			}
			return tab;
		default:
			return tab;
		}
	}

}
