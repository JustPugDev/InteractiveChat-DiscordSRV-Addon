package com.loohp.interactivechatdiscordsrvaddon.Metrics;

import java.util.concurrent.Callable;

import com.loohp.interactivechatdiscordsrvaddon.InteractiveChatDiscordSrvAddon;

public class Charts {
	
	public static void setup(Metrics metrics) {
		
		metrics.addCustomChart(new Metrics.SimplePie("item_image_view_enabled", new Callable<String>() {
	        @Override
	        public String call() throws Exception {
	        	String string = "Disabled";
	        	if (InteractiveChatDiscordSrvAddon.plugin.getConfig().getBoolean("InventoryImage.Item.Enabled")) {
	        		string = "Enabled";
	        	}
	            return string;
	        }
	    }));
		
		metrics.addCustomChart(new Metrics.SimplePie("inventory_image_view_enabled", new Callable<String>() {
	        @Override
	        public String call() throws Exception {
	        	String string = "Disabled";
	        	if (InteractiveChatDiscordSrvAddon.plugin.getConfig().getBoolean("InventoryImage.Inventory.Enabled")) {
	        		string = "Enabled";
	        	}
	            return string;
	        }
	    }));
		
		metrics.addCustomChart(new Metrics.SimplePie("enderchest_image_view_enabled", new Callable<String>() {
	        @Override
	        public String call() throws Exception {
	        	String string = "Disabled";
	        	if (InteractiveChatDiscordSrvAddon.plugin.getConfig().getBoolean("InventoryImage.EnderChest.Enabled")) {
	        		string = "Enabled";
	        	}
	            return string;
	        }
	    }));
		
		metrics.addCustomChart(new Metrics.SingleLineChart("total_messages_processed_per_interval", new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
            	long amount = InteractiveChatDiscordSrvAddon.plugin.messagesCounter.getAndSet(0);
                return amount > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) amount;
            }
        }));
		
		metrics.addCustomChart(new Metrics.SingleLineChart("total_inventory_images_created_per_interval", new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
            	long amount = InteractiveChatDiscordSrvAddon.plugin.imageCounter.getAndSet(0);
                return amount > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) amount;
            }
        }));
		
	}

}
